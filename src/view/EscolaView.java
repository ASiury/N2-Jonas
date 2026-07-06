package view;

import control.Controller;
import java.util.Scanner;


public class EscolaView {
    private EscolaController control = new EscolaController();

    public void iniciar(){
        Scanner ler = Utilidades.getScanner();

        byte opcao = -1;
        while(opcao != 0) {

            menuInicial();
            opcao = ler.nextByte();
            ler.nextLine();
            if (opcao < 0 || opcao > 4) {
                throw new ForaLimiteException();
            }
            processarOpcao(opcao);
        }
    }

    public void menuInicial() {
        System.out.println("Programa de controle escolar");
        System.out.println("----------------------------");
        System.out.println("1 - Gerenciar corpo doscente");
        System.out.println("2 - Gerenciar corpo discente");
        System.out.println("3 - Gerenciar disciplinas");
        System.out.println("4 - Gerenciar turmas");
        System.out.println("0 - Fechar programa");
    }
    private void menuDisciplinas() {
        System.out.println("Opções de disciplinas");
        System.out.println("-------------------------------");
        System.out.println("1) Cadastrar disciplina");
        System.out.println("2) Remover disciplina");
        System.out.println("3) Relatório de disciplinas");
        System.out.println("0) Voltar");
    }
    private void menuDocente() {
        System.out.println("Opções de professor");
        System.out.println("-------------------------------");
        System.out.println("1) Cadastrar profesor");
        System.out.println("2) Remover professor");
        System.out.println("3) Alterar disciplina ministrada por professor");
        System.out.println("4) Relatório dos professores");
        System.out.println("0) Voltar");
    }
    private void menuDiscente() {
        System.out.println("Opções de aluno");
        System.out.println("-------------------------------");
        System.out.println("1) Cadastrar aluno");
        System.out.println("2) Remover aluno");
        System.out.println("3) Atribuir notas para aluno");
        System.out.println("4) Alterar situação de matrícula do aluno");
        System.out.println("5) Relatório de alunos");
        System.out.println("0) Voltar");
    }
    private void menuTurma() {
        System.out.println("Opções de turma");
        System.out.println("-------------------------------");
        System.out.println("1) Cadastrar turma");
        System.out.println("2) Remover turma");
        System.out.println("3) Inserir aluno em turma");
        System.out.println("4) Remover aluno de turma");
        System.out.println("5) Inserir professor em turma");
        System.out.println("6) Remover professor de turma");
        System.out.println("7) Inserir disciplina em turma");
        System.out.println("8) Remover disciplina de turma");
        System.out.println("9) Relatório de turmas");
        System.out.println("0) Voltar");
    }

    public void processarOpcao(byte opcao) {
        Scanner ler = Utilidades.getScanner();
        byte o;
        switch (opcao) {
            case 0:
                System.out.println("Fechando o programa...");
                System.exit(0);
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
