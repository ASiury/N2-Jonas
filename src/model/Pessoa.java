package model;

import util.Utilidades;

public abstract class Pessoa {
    private String nome;
    private String cpf;
    private String Matricula;
    public Pessoa(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;

        this.Matricula = Utilidades.gerarMatricula();
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getMatricula() {
        return Matricula;
    }
}
