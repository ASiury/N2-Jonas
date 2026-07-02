package view;
import control.Controller;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        EscolaView view = new EscolaView(new Controller());
        view.iniciar();
    }
}
