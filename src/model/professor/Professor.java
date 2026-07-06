package model.professor;

import model.Disciplina;
import model.Pessoa;

public abstract class Professor extends Pessoa {
    private double salarioBase;
    private String formacao;
    private int qtdTurmas = 0;

    public Professor(String nome, String cpf, String disciplina) {
        super(nome, cpf);

        this.formacao = disciplina;
    }

    public String getFormacao() {
        return formacao;
    }

    public abstract double calcularSalario();

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public int getQtdTurmas() {
        return qtdTurmas;
    }

    public void setQtdTurmas(int qtdTurmas) {
        this.qtdTurmas = qtdTurmas;
    }

    @Override
    public String toString() {
        return this.getNome() +" CPF:"+ this.getCpf();
    }
}

