package model;

import model.professor.Professor;

import java.util.*;

public class Turma {
    private String codigo;
    private int anoEscolar;
    private char turno;

    private ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
    private ArrayList<Professor> professores = new ArrayList<Professor>();
    private ArrayList<String> salas = new ArrayList<String>();
    private ArrayList<Aluno> alunos = new ArrayList<Aluno>();

    public Turma(String codigo, int anoEscolar, String sala, char turno, Disciplina disciplina) {
        this.turno = turno;
        this.codigo = codigo;
        this.anoEscolar = anoEscolar;

        this.salas.add(sala);
        this.disciplinas.add(disciplina);
    }

    public int getAnoEscolar() {
        return anoEscolar;
    }

    public String getCodigo() {
        return codigo;
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public ArrayList<String> getSalas() {
        return salas;
    }

    public ArrayList<Disciplina> getDisciplinas() {
        return this.disciplinas;
    }

    public ArrayList<Professor> getProfessores() {
        return this.professores;
    }

    public Character getTurno() {
        return turno;
    }

    public void addAluno(Aluno aluno) {
        this.alunos.add(aluno);
    }

    public void removeAluno(Aluno aluno) {
        this.alunos.remove(aluno);
    }

    public void addDisciplina(Disciplina d) {
        this.disciplinas.add(d);
    }

    public void removeDisciplina(Disciplina d){
        this.disciplinas.remove(d);
    }

    public void addProfessor(Professor p) {
        this.professores.add(p);
    }

    public void removeProfessor(Professor p) {
        this.professores.remove(p);
    }

    public boolean hasAluno(Aluno aluno) {
        return this.alunos.contains(aluno);
    }
    public boolean hasDisciplina(Disciplina disciplina) {
        return this.disciplinas.contains(disciplina);
    }

    public StringBuilder getRelatorio(){
        StringBuilder s = new StringBuilder();
        s.append("Ano: ").append(this.anoEscolar).append("\n");
        s.append("Turno: ").append(this.turno).append("\n");
        s.append("Código: ").append(this.codigo).append("\n");
        s.append("Sala: ").append(this.salas).append("\n");
        s.append("Diciplinas: ").append(this.disciplinas.toString()).append("\n");
        s.append("Professores: ").append(this.professores.toString()).append("\n");
        s.append("Alunos:").append(this.alunos.toString()).append("\n");
        return s;
    }
}
