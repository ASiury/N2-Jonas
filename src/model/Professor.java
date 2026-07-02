package model;

public class Professor extends Pessoa{
    private double salario;
    private String disciplina;

    public Professor(String nome, String cpf) {
        super(nome, cpf);
    }

    public double getSalario() {
        return salario;
    }

    public String getDisciplina() {
        return disciplina;
    }
}
