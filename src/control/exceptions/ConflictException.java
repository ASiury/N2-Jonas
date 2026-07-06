package control.exceptions;

public class ConflictException extends EscolaRuntimeException {
    public ConflictException(String message) {
        super(message);
    }
    public ConflictException(String item, String value, String article) {
        super(item + " " + value + " " + article + " já está cadastrado no sistema.");
    }
}
