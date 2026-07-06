package view;

import control.EscolaController;
import control.exceptions.EscolaException;
import control.exceptions.ForaLimiteException;
import util.Utilidades;

import java.util.ArrayList;
import java.util.Objects;
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
                do {
                    menuDocente();
                    o = ler.nextByte();
                    ler.nextLine();
                    try {
                        if (o == 1) {
                            System.out.println("Informe o nome do professor");
                            String nome = ler.nextLine();
                            System.out.println("Informe o CPF do professor");
                            String cpf = ler.nextLine();
                            System.out.println("Informe a formacao do professor");
                            String formacao = ler.nextLine();

                            if (formacao.equals("Pedagogia")) {
                                System.out.println(control.cadastrarProfessorPolivalente(nome, cpf, formacao));
                            } else {
                                System.out.println("Informe o código da disciplina ministrada pelo professor:");
                                String disciplina = ler.nextLine();
                                System.out.println(control.cadastrarProfessorEspecialista(nome, cpf, formacao, disciplina));
                            }
                        } else if (o == 2) {
                            System.out.println("Informe o CPF do professor a ser removido:");
                            String cpf = ler.nextLine();
                            System.out.println(control.removerProfessor(cpf));
                        } else if (o == 3) {
                            System.out.println("Informe o CPF do professor a ter a dsiciplina alterada:");
                            String cpf = ler.nextLine();
                            System.out.println("Informe o código da nova disciplina a ser ministrada pelo professor:");
                            String titulo = ler.nextLine();
                            System.out.println(control.setarDisciplinaProfessor(cpf, titulo));
                        } else if (o == 4) {
                            System.out.println(control.relatorioProfessores());
                        }
                    } catch (EscolaException e) {
                        System.out.println("Erro: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Erro inesperado ou entrada inválida. Tente novamente.");
                        ler.nextLine();
                    }
                } while (o != 0);
                break;
            case 2:
                do {
                    menuDiscente();
                    o = ler.nextByte();
                    ler.nextLine();
                    try {
                        if (o == 1) {
                            System.out.println("Informe o nome do aluno:");
                            String aluno = ler.nextLine();
                            System.out.println("Informe o CPF do aluno:");
                            String cpf = ler.nextLine();
                            System.out.println("Informe a serie do aluno:");
                            int serie = ler.nextInt();
                            ler.nextLine();
                            System.out.println(control.cadastrarAluno(aluno, cpf, serie));
                        } else if (o == 2) {
                            System.out.println("Informe o CPF do aluno:");
                            String aluno = ler.nextLine();
                            System.out.println(control.removerAluno(aluno));
                        } else if (o == 3) {
                            String codigo;
                            System.out.println("Informe o CPF do aluno:");
                            String cpf = ler.nextLine();
                            do {
                                System.out.println("Informe o código da disciplina ou '0' para parar inserção:");
                                codigo = ler.nextLine();
                                System.out.println("Informe a nota obtida:");
                                double nota = ler.nextDouble();
                                ler.nextLine();
                                System.out.println(control.setNotas(cpf, codigo, nota));
                            } while (!Objects.equals(codigo, "0"));
                        } else if (o == 4) {
                            System.out.println("Informe o CPF do aluno:");
                            String cpf = ler.nextLine();
                            System.out.println("Digite '1' para ativar a matricula; e '2' para trancá-la");
                            byte num = ler.nextByte();
                            ler.nextLine();
                            System.out.println(control.setStatusMatricula(cpf, num));
                        } else if (o == 5) {
                            System.out.println(control.relatorioAlunos());
                        }
                    } catch (EscolaException e) {
                        System.out.println("Erro: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Erro inesperado ou entrada inválida. Tente novamente.");
                        ler.nextLine();
                    }
                } while (o != 0);

                break;
            case 3:
                do {
                    menuDisciplinas();
                    o = ler.nextByte();
                    ler.nextLine();
                    try {
                        if (o == 1) {
                            System.out.println("Informe o nome da disciplina:");
                            String disciplina = ler.nextLine();
                            System.out.println("Informe o codigo da disciplina:");
                            String code = ler.nextLine();
                            int carga;
                            do {
                                System.out.println("Informe a carga horária da disciplina:");
                                carga = ler.nextInt();
                                if(carga<30) System.out.println("Valor muito baixo informado.");
                            } while(carga < 30);
                            ler.nextLine();
                            System.out.println(control.cadastrarDisciplina(disciplina, code, carga));
                        } else if (o == 2) {
                            System.out.println("Informe o código da disciplina:");
                            String disciplina = ler.nextLine();
                            System.out.println(control.removerDisciplina(disciplina));
                        } else if (o == 3) {
                            System.out.println(control.relatorioDisciplinas());
                        }
                    } catch (EscolaException e) {
                        System.out.println("Erro: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Erro inesperado ou entrada inválida. Tente novamente.");
                        ler.nextLine();
                    }
                } while (o != 0);
                break;
            case 4:
                do {
                    menuTurma();
                    o = ler.nextByte();
                    ler.nextLine();
                    try{
                        if (o == 1) {
                            System.out.println("Informe o codigo da turma");
                            String turma = ler.nextLine();
                            System.out.println("Ano escolar da turma:");
                            int ano = ler.nextInt();
                            ler.nextLine();
                            System.out.println("Sala dedicada à turma:");
                            String sala = ler.nextLine();
                            System.out.println("Turno de funcionamento (M- matutino/V- verspertino)");
                            char turno = ler.next().charAt(0);
                            ler.nextLine();
                            System.out.println("Insira os códigos de disciplinas. Digite '0' para finalizar:");
                            String codDisc = ler.nextLine();
                            System.out.println(control.cadastrarTurma(ano, turma, sala, codDisc, turno));
                        }
                        else if (o == 2) {
                            System.out.println("Informe o codigo da turma:");
                            String turma = ler.nextLine();
                            System.out.println(control.removerTurma(turma));
                        }
                        else if (o == 3) {
                            System.out.println("Informe o codigo da turma:");
                            String turma = ler.nextLine();
                            System.out.println("Informe o CPF do aluno a ser inserido:");
                            String cpf = ler.nextLine();
                            System.out.println(control.vincularAlunoTurma(turma, cpf));
                        }
                        else if (o == 4) {
                            System.out.println("Informe o codigo da turma:");
                            String turma = ler.nextLine();
                            System.out.println("Informe o CPF do aluno a ser removido:");
                            String cpf = ler.nextLine();
                            System.out.println(control.desvincularAlunoTurma(turma, cpf));
                        } else if (o == 5) {
                            System.out.println("Informe o codigo da turma:");
                            String turma = ler.nextLine();
                            System.out.println("Informe o CPF do professor a ser inserido:");
                            String professor = ler.nextLine();
                            System.out.println(control.vincularProfessorTurma(turma, professor));
                        } else if (o == 6) {
                            System.out.println("Informe o codigo da turma:");
                            String turma = ler.nextLine();
                            System.out.println("Informe o CPF do professor a ser removido:");
                            String professor = ler.nextLine();
                            System.out.println(control.desvincularProfessorTurma(turma, professor));
                        }  else if (o == 7) {
                            System.out.println("Informe o codigo da turma:");
                            String turma = ler.nextLine();
                            System.out.println("Informe o código da disciplina a ser inserida:");
                            String disciplina = ler.nextLine();
                            System.out.println(control.vincularDisciplinaTurma(disciplina, turma));
                        }  else if (o == 8) {
                            System.out.println("Informe o codigo da turma:");
                            String turma = ler.nextLine();
                            System.out.println("Informe o código da disciplina a ser removida:");
                            String disciplina = ler.nextLine();
                            System.out.println(control.desvincularDisciplinaTurma(disciplina, turma));
                        } else if (o == 9) {
                            System.out.println(control.relatorioTurmas());
                        }
                    } catch (EscolaException e) {
                        System.out.println("Erro: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Erro inesperado ou entrada inválida. Tente novamente.");
                        ler.nextLine();
                    }
                } while (o != 0);
                break;
            default:
                System.out.println("Opcao invalida");
        }
    }
}