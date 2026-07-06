package model.professor;
import util.Utilidades;

public class ProfessorPolivalente extends Professor {
    public ProfessorPolivalente(String nome, String cpf, String formacao) {
       super(nome, cpf, formacao);
    }

    @Override
    public double calcularSalario() {
        return Utilidades.salarioBase() + 200 * getQtdTurmas();
    }

    @Override
    public String getRelatorio() {
        String s = "";
        s += "Professor: "+ this.getNome() +"\n";
        s += "CPF: "+ this.getCpf() + "\n";
        s += "Formacao: "+ this.getFormacao()+"\n";
        s += "Salário: " + this.calcularSalario()+"\n";
        return s;
    }
}
