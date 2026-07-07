package control.exceptions;

public class ConflictException extends EscolaRuntimeException {
    public ConflictException(String item, String value, String artigo) {
        super(item + " " + value + " " + " já está cadastrad" +artigo+" no sistema.");
    }
}
