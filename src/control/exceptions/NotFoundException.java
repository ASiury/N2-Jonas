package control.exceptions;

public class NotFoundException extends EscolaRuntimeException {
    public NotFoundException(String mensagem) {
        super(mensagem);
    }
}
