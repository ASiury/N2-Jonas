package control.exceptions;

public class ErroVinculoException extends EscolaException {
    public ErroVinculoException() {
        super("Erro ao tentar realizar o vínculo. Os dados são incompatíveis.");
    }

    public ErroVinculoException(String message) {
        super(message);
    }
}
