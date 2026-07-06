package control.exceptions;

public class CpfInvalidoException extends EscolaException {
    public CpfInvalidoException() {
        super("O CPF informado é inválido ou não corresponde a uma pessoa cadastrada corretamente.");
    }

    public CpfInvalidoException(String message) {
        super(message);
    }
}
