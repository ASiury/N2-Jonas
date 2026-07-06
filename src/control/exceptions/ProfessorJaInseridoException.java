package control.exceptions;

public class ProfessorJaInseridoException extends EscolaException {
    public ProfessorJaInseridoException() {
        super("Este professor já foi inserido na turma.");
    }
}
