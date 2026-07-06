package model;

import util.Utilidades;

import java.util.HashMap;

public class Aluno extends Pessoa {
    private String matricula;
    private int serie;
    private StatusMatricula statusMatricula =  StatusMatricula.CADASTRO;
    private HashMap<Disciplina, Double> notas = new HashMap<>();

    public Aluno(String nome, String cpf, int serie) {
        super(nome, cpf);
        this.matricula = Utilidades.gerarMatricula();
        this.serie = serie;
    }

    public void setStatus(byte num){
        if(num == 1){
            this.statusMatricula = StatusMatricula.ATIVO;
        } else if(num == 2){
            this.statusMatricula = StatusMatricula.TRANCADO;
        }
    }

    public int getSerie() {
        return serie;
    }

    public String getMatricula() {
        return matricula;
    }

    public StatusMatricula getStatus(){
       return statusMatricula;
    }

    public HashMap<Disciplina, Double> getNotas() {
        return notas;
    }

    public void setNotas(Disciplina d, double nota) {
        this.notas.put(d, nota);
    }

    public String getRelatorio(){
        StringBuilder s = new StringBuilder();
        s.append("Professor: ").append(this.getNome()).append("\n");
        s.append("Matricula: ").append(this.getMatricula()).append("\n");
        s.append("CPF: ").append(this.getCpf()).append("\n");
        s.append("Serie: ").append(this.getSerie()).append("\n");
        s.append("Status da matrícula: ").append(this.getStatus()).append("\n");
        for(Disciplina d: this.getNotas().keySet()){
            s.append("--------------------------------\n");
            s.append("Disciplina: ").append(d.getNome()).append("\n nota: ").append(this.getNotas().get(d)).append("\n");
        }
        return s.toString();
    }

    @Override
    public String toString() {
        return this.getNome() + " - Matrícula: " + this.getMatricula();
    }
    }
}
