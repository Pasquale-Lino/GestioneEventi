package pasquale.alberico.GestioneEventi.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pasquale.alberico.GestioneEventi.Exceptions.UnauthorizedException;

import java.io.IOException;
import org.springframework.util.AntPathMatcher;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWTTools jwtTools;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            throw new UnauthorizedException("Header dell'autorizzazione mancante o non corretta");
        }
        String token = header.replace("Bearer ", "");
        jwtTools.verifyToken(token);

        chain.doFilter(request, response);

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return new AntPathMatcher().match("/auth/**", request.getServletPath())
                || ("GET".equals(request.getMethod()) && new AntPathMatcher().match("/events/**", request.getServletPath()));
    }
}
