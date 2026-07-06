package control.exceptions;

public class ProfessoresInsuficientesException extends EscolaException {
    public ProfessoresInsuficientesException() {
        super("Não há professores suficientes na turma para realizar a remoção.");
    }
}
