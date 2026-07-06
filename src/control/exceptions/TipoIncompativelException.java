package control.exceptions;

public class TipoIncompativelException extends EscolaException {
    public TipoIncompativelException(String s) {
        super("O tipo do objeto é incompatível para esta operação.");
    }

    public TipoIncompativelException(String nome, String tipo) {
        super(nome + " não é um " + tipo + " válido.");
    }
}
