package control.exceptions;

public class ForaLimiteException extends EscolaException {
    public ForaLimiteException() {
        super("Opção fora dos limites permitidos.");
    }
}
