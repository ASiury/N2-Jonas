package model;

public class Disciplina {
    private String nome;
    private String codigo;
    private int cargaHoraria;

    public Disciplina(String nome, String codigo, int cargaHoraria) {
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
    }

    public String getNome() {
        return nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public String getCodigo() {
        return codigo;
    }

    public StringBuilder getRelatorio() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(this.nome).append("\n");
        sb.append("Codigo: ").append(this.codigo).append("\n");
        sb.append("Carga horária: ").append(this.cargaHoraria).append("\n");
        return sb;
    }

    @Override
    public String toString() {
        return nome + " (" + codigo + ")";
    }
}
