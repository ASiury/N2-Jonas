package model;

import java.util.*;

public class Turma {
    private ArrayList<String> salas  = new ArrayList<String>();
    private ArrayList<Professor> professores = new ArrayList <Professor>();
    private ArrayList<Aluno> alunos  = new ArrayList<Aluno>();

    public Turma(ArrayList<String> salas, ArrayList<Professor> professores, ArrayList<Aluno> alunos) {
        this.salas = salas;
        this.professores = professores;
        this.alunos = alunos;
    }

    public ArrayList<String> getSalas() {
        return this.salas;
    }

    public ArrayList<Aluno> getAlunos() {
        return this.alunos;
    }

    public ArrayList<Professor> getProfessores() {
        return this.professores;
    }
}
