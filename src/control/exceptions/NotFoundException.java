package control.exceptions;

public class NotFoundException extends EscolaRuntimeException {
    public NotFoundException(String mensagem) {
        super(mensagem);
    }

    public NotFoundException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
