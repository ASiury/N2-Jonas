package model.professor;

import model.Disciplina;
import util.Utilidades;

import java.util.ArrayList;

public class ProfessorEspecialista extends Professor {
    private Disciplina disciplina;

    public ProfessorEspecialista(String nome, String cpf, String formacao, Disciplina disciplina) {
        super(nome, cpf, formacao);
        this.disciplina =  disciplina;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setarDisciplinas(Disciplina disciplina) {
        this.disciplina =  disciplina;
    }

    @Override
    public double calcularSalario() {
        return Utilidades.salarioBase() + 2500;
    }

    @Override
    public String getRelatorio() {
        String s = "";
        s += "Professor: "+ this.getNome()+"\n";
        s += "CPF: "+ this.getCpf()+"\n";
        s += "Formacao: "+ this.getFormacao()+"\n";
        s += "Disciplina ministrada: "+ this.getDisciplina().getNome()+"\n";
        s += "Salário: " + this.calcularSalario()+"\n";
        return s;
    }


}
