package pasquale.alberico.GestioneEventi.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,Object> notFound(NotFoundException ex){
        return Map.of("error", ex.getMessage(),"timestamp", LocalDateTime.now());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> badRequest(BadRequestException ex){
        return Map.of("error", ex.getMessage(), "timestamp", LocalDateTime.now());
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> serverError(Exception ex){
        ex.printStackTrace();
        return Map.of("error","Internal server error", "detail", ex.getMessage(), "timestamp", LocalDateTime.now());
    }
}
