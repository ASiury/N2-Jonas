package control.exceptions;

public class LimiteProfessorAtingidoException extends EscolaException {
    public LimiteProfessorAtingidoException() {
        super("O limite de professores permitidos para esta turma já foi atingido.");
    }
}
