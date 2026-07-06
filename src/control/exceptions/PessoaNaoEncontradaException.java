package control.exceptions;

public class PessoaNaoEncontradaException extends EscolaException {
    public PessoaNaoEncontradaException() {
        super("Pessoa não encontrada no sistema.");
    }

    public PessoaNaoEncontradaException(String message) {
        super(message);
    }
}
