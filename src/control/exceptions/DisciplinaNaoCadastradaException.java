package control.exceptions;

public class DisciplinaNaoCadastradaException extends EscolaException {
    public DisciplinaNaoCadastradaException() {
        super("A disciplina não está cadastrada no sistema.");
    }

    public DisciplinaNaoCadastradaException(String message) {
        super(message);
    }
}
