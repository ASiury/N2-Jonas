package control.exceptions;

public class ValorIncompativelException extends EscolaException {
    public ValorIncompativelException() {
        super("O valor informado é incompatível ou nulo.");
    }

    public ValorIncompativelException(String message) {
        super(message);
    }
}
