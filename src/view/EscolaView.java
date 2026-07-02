package view;

import control.Controller;
import java.util.Scanner;

public class EscolaView{
    private final Controller control;
    private final Scanner ler;

    public EscolaView(Controller control) {
        this.control = control;
        this.ler = new Scanner(System.in);
    }

    public void iniciar(){
        byte opcao;
        do{
            System.out.println("Pressione enter para prosseguir.");
            opcao = Byte.parseByte(ler.nextLine());
            processarOpcao(opcao);
            menuIniciar();
        } while (opcao != 0);
        ler.close();
    }

    private void processarOpcao(byte opcao){
        String nome, cpf;
        double salario;
        switch(opcao){
            case 1:
                System.out.println("Nome: ");
                nome = ler.nextLine();
                System.out.println("CPF: ");
                cpf = ler.nextLine();
                System.out.println("Funçao na instituição: ");
                System.out.println("A- aluno, B- professor, C- Outro");

                char f = ler.nextLine().charAt(0);

                if(f != 'a' || f != 'A' || f != 'b' || f != 'B'){
                    throw new IllegalArgumentException(
                            "Caractér inválido '" + f + "', esperado A, B ou C."
                    );
                } else if(f == 'C' || f == 'c') {
                    control.cadastrarPessoa(nome, cpf);
                }
        }
    }

    public void menuIniciar(){
        System.out.println("Selecione uma opcao:");
        System.out.println("1- Inserir pessoa no sistema.");
        System.out.println("2- Alterar pessoa no sistema.");
        System.out.println("3- Remover pessoa no sistema.");
    }
}
