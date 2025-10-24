package pasquale.alberico.GestioneEventi.Exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {super(message);}
    public NotFoundException(Object id) {super("Not found: "+id);}
}
