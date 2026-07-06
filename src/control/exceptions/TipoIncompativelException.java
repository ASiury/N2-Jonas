package control.exceptions;

public class TipoIncompativelException extends EscolaRuntimeException {
    public TipoIncompativelException(String message) {
        super(message);
    }
    public TipoIncompativelException(String message, Throwable cause) {
        super(message, cause);
    }
}
