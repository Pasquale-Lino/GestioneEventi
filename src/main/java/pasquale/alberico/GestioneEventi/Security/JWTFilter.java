package pasquale.alberico.GestioneEventi.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pasquale.alberico.GestioneEventi.Entities.User;
import pasquale.alberico.GestioneEventi.Exceptions.UnauthorizedException;
import pasquale.alberico.GestioneEventi.Services.UsersService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JWTTools jwtTools;
    private final UsersService usersService;

    public JWTFilter(JWTTools jwtTools, @Lazy UsersService usersService) {
        this.jwtTools = jwtTools;
        this.usersService = usersService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        if (shouldNotFilter(request)) {
            chain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            throw new UnauthorizedException("Header dell'autorizzazione mancante o non corretta");
        }

        String token = header.replace("Bearer ", "");
        jwtTools.verifyToken(token);

        UUID userId = jwtTools.extractIdFromToken(token);
        User found = usersService.findById(userId);

        List<SimpleGrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("ROLE_" + found.getRole().name())
        );

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(found, null, authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return new AntPathMatcher().match("/auth/**", request.getServletPath())
                || ("GET".equals(request.getMethod()) && new AntPathMatcher().match("/events/**", request.getServletPath()));
    }
}
