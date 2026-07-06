package control.exceptions;

public class ProfessorIncompativelException extends EscolaException {
    public ProfessorIncompativelException() {
        super("O perfil do professor é incompatível com a turma (ex: polivalente em turma de especialista).");
    }
}
