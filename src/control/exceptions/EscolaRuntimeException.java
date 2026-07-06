package control.exceptions;

public class EscolaRuntimeException extends RuntimeException {
    public EscolaRuntimeException(String message) {
        super(message);
    }
    public EscolaRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
