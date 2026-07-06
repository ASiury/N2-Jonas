package control.exceptions;

public class JaCadastradoException extends EscolaException {
    public JaCadastradoException(String message) {
        super(message);
    }

    public JaCadastradoException(String item, String valor, String artigo) {
        super(item + " " + valor + " já está cadastrad" + artigo + " no sistema.");
    }
}
