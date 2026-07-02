package model;

public class Aluno extends Pessoa {
    private String meio;

    public Aluno(String nome, String cpf) {
        super(nome, cpf);
    }

    public String getMeio(){
        return meio;
    }
}
