package control.exceptions;

public class DisciplinaIncompativelException extends EscolaException {
    public DisciplinaIncompativelException() {
        super("A disciplina informada é incompatível com a turma ou professor.");
    }

    public DisciplinaIncompativelException(String message) {
        super(message);
    }
}
