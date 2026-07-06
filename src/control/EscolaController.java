package control;

import control.exceptions.*;
import model.Aluno;
import model.Disciplina;
import model.Pessoa;
import model.Turma;
import model.professor.Professor;
import model.professor.ProfessorEspecialista;
import model.professor.ProfessorPolivalente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EscolaController {
    private Map<String, Pessoa> pessoaMap = new HashMap<>();
    private Map<String, Disciplina> disciplinaMap = new HashMap<>();
    private Map<String, Turma> turmaMap = new HashMap<>();

    public void verificarSistema(String cpf){
        if(pessoaMap.containsKey(cpf)){
            throw new JaCadastradoException("O CPF", cpf, "o");
        }
    }

    public String cadastrarAluno(String nome, String cpf, int serie){
        verificarSistema(cpf);
        Aluno a = new Aluno(nome, cpf, serie);
        pessoaMap.put(cpf, a);
        return "Aluno cadastrado com sucesso!";
    }
    public String removerAluno(String cpf) {
        if (cpf == null) {
            throw new ValorIncompativelException();
        }
        Pessoa pessoa = pessoaMap.get(cpf);
        if (pessoa == null) {
            throw new PessoaNaoEncontradaException();
        }
        if (!(pessoa instanceof Aluno)) {
            throw new TipoIncompativelException(pessoaMap.get(cpf).getNome() + "aluno");
        }
        pessoaMap.remove(cpf);
        return "Aluno removido com sucesso!";
    }
    public String setNotas(String cpf, String codigo, double nota) {
        if (cpf == null || codigo == null) {
            throw new ValorIncompativelException();
        }
        Pessoa p = pessoaMap.get(cpf);
        if (!(p instanceof Aluno a)) {
            throw new ErroVinculoException();
        }

        boolean disciplinaEncontrada = false;
        Disciplina d = disciplinaMap.get(codigo);
        if(d == null) throw new DisciplinaNaoCadastradaException();
        
        for(Turma t: turmaMap.values()){
            if(t.hasAluno(a) && t.hasDisciplina(d)){
                disciplinaEncontrada = true;
                break;
            }
        }
        if(!disciplinaEncontrada){
            throw new DisciplinaIncompativelException();
        }

        a.setNotas(d, nota);
        return "Você definiu a nota do aluno " + a.getNome() + " como " + nota + " na disciplina " + d.getNome() + ".";
    }
    public String setStatusMatricula(String cpf, byte num) {
        if (!(pessoaMap.get(cpf) instanceof Aluno a)) throw new CpfInvalidoException();

        String s = "";
        if (num == 1) s = "ativada";
        else if (num == 2) s = "trancada";
        a.setStatus(num);
        return "A matricula do aluno " + a.getNome() + " foi " + s + " com sucesso.";
    }

    public String removerProfessor(String cpf) {
        if (cpf == null) {
            throw new ValorIncompativelException();
        }
        Pessoa pessoa = pessoaMap.get(cpf);
        if (pessoa == null) {
            throw new PessoaNaoEncontradaException();
        }
        if (!(pessoa instanceof Professor)) {
            throw new TipoIncompativelException(pessoaMap.get(cpf).getNome() + "aluno");
        }
        pessoaMap.remove(cpf);
        return "Professor removido com sucesso!";
    }
    public String cadastrarProfessorPolivalente(String nome, String cpf, String formacao){
        verificarSistema(cpf);
        Professor p = new ProfessorPolivalente(nome, cpf, formacao);
        if(!formacao.equals("Pedagogia")){
            throw new TipoIncompativelException(pessoaMap.get(cpf).getNome() + "professor");
        }
        pessoaMap.put(cpf, p);
        return "Professor cadastrado com sucesso!";
    }
    public String cadastrarProfessorEspecialista(String nome, String cpf, String formacao, String codigoDisciplina){
        Disciplina d = disciplinaMap.get(codigoDisciplina);
        if(d == null) throw new DisciplinaNaoCadastradaException();
        verificarSistema(cpf);
        Professor p = new ProfessorEspecialista(nome, cpf, formacao, d);
        if(formacao.equals("Pedagogia")){
           throw new TipoIncompativelException(pessoaMap.get(cpf).getNome() + "professor");
        }
        pessoaMap.put(cpf, p);
        return "Professor cadastrado com sucesso!";
    }
    public String setarDisciplinaProfessor(String cpf, String code){
        if(code == null || cpf == null) {
            throw new ValorIncompativelException();
        }
        ProfessorEspecialista p = (ProfessorEspecialista) pessoaMap.get(cpf);
        Disciplina d = disciplinaMap.get(code);
        p.setarDisciplinas(d);
        return "Disciplina "+d.getNome()+" foi atribuída para "+p.getNome()+" com sucesso!";
    }

    public String removerDisciplina(String codigo){
        if(codigo == null) throw new ValorIncompativelException();
        Disciplina d = disciplinaMap.get(codigo);
        if(d == null) throw new ValorIncompativelException();
        String nome =  d.getNome();
        disciplinaMap.remove(codigo);

        return "Disciplina "+nome+" removida do sistema.";
    }
    public String cadastrarDisciplina(String nome, String codigo, int cargaHoraria){
        if(codigo == null) throw new ValorIncompativelException();
        if(disciplinaMap.get(codigo) != null) throw new DisciplinaJaCadastradaException( codigo +" já foi cadastrado no sistema.");
        Disciplina d = new Disciplina(nome, codigo, cargaHoraria);
        disciplinaMap.put(codigo, d);
        return "Disciplina "+d.getNome()+" criada com sucesso!";
    }

public String cadastrarTurma(int anoEscolar, String codigo, String sala, ArrayList<String> codigoDisciplina, char turno) {
        if (codigo == null || codigoDisciplina == null || sala == null) {
            throw new ValorIncompativelException();
        }
        ArrayList<Disciplina> d = new ArrayList<>();
        boolean eNulo = true;
        for (String s : codigoDisciplina) {
            if (s != null) {
                Disciplina di = disciplinaMap.get(s);
                if (di == null) throw new DisciplinaNaoCadastradaException();
                d.add(di);
                eNulo = false;
            }
        }
        if(eNulo) throw new DisciplinaNaoCadastradaException();

        if (turmaMap.containsKey(codigo)) {
            throw new JaCadastradoException("A turma de código", codigo, "a");
        }
        for(Turma t: turmaMap.values()){
            for(int i = 0; i < t.getSalas().size(); i++){
                if(t.getSalas().get(i).equals(sala) && t.getTurno().equals(turno)) {
                    throw new SalaIndisponivelException("Esta turma não pode estudar nesta sala no turno especificado!");
                }
            }
        }
        Turma t = new Turma(codigo, anoEscolar, sala, turno, d);
        turmaMap.put(codigo, t);
        return "Nova turma "+t.getCodigo()+" criada com sucesso!";
    }
    public String removerTurma(String codigo) {
        if (codigo == null) {
            throw new ValorIncompativelException();
        }
        Turma t = turmaMap.remove(codigo);
        if (t != null) {
            return "Turma removida com sucesso!";
        } else {
            return "Erro: Turma não encontrada no sistema.";
        }
    }
    public String vincularAlunoTurma(String codigo, String cpf){
        if(!pessoaMap.containsKey(cpf)) throw new ErroVinculoException("Aluno não matriculado.");
        if(!(pessoaMap.get(cpf) instanceof Aluno)) throw new TipoIncompativelException(pessoaMap.get(cpf).getNome() + " não é aluno");
        Turma t = turmaMap.get(codigo);
        if(t == null) throw new ValorIncompativelException("Turma não encontrada.");
        Aluno a = (Aluno)pessoaMap.get(cpf);

        if(a.getSerie() != t.getAnoEscolar()) throw new ErroVinculoException("Ano de estudo incompatível.");

        t.addAluno(a);
        return "Aluno inserido na turma "+t.getCodigo()+".";
    }
    public String desvincularAlunoTurma(String codigo, String cpf){
        if(!pessoaMap.containsKey(cpf)) throw new PessoaNaoEncontradaException();
        if(!(pessoaMap.get(cpf) instanceof Aluno a)) throw new TipoIncompativelException(pessoaMap.get(cpf).getNome()+ "aluno");

        Turma t = turmaMap.get(codigo);

        t.removeAluno(a);
        return "Aluno removido na turma "+t.getCodigo()+".";
    }
    public String vincularProfessorTurma(String codigo, String cpf){
        Turma t = turmaMap.get(codigo);
        if(t == null) throw new ValorIncompativelException("Turma não encontrada.");
        Professor p = (Professor)pessoaMap.get(cpf);

        if(t.getAnoEscolar() >= 1 && t.getAnoEscolar() <= 5 ){
            if(!(p instanceof ProfessorPolivalente)) throw new ErroVinculoException();
            if(t.getProfessores().size() == 1) throw new LimiteProfessorAtingidoException();
            t.addProfessor(p);
            p.setQtdTurmas(p.getQtdTurmas()+1);
            System.out.println("Professor "+p.getNome()+" inserido na turma "+t.getCodigo()+".");
        }
        else if(t.getAnoEscolar() >= 6 && t.getAnoEscolar() <= 9 ){
            if(!(p instanceof ProfessorEspecialista)) throw new ErroVinculoException();
            if(t.getProfessores().size() == t.getDisciplinas().size()) throw new LimiteProfessorAtingidoException();
            for(Professor p1:t.getProfessores()){
                if(p1.getCpf().equals(cpf)){
                    throw new ProfessorJaInseridoException();
                }
            }
            boolean temDisciplina = false;
            for(Disciplina d: t.getDisciplinas()){
                if(Objects.equals(((ProfessorEspecialista) p).getDisciplina().getCodigo(), d.getCodigo())){
                    temDisciplina = true;
                    break;
                }
            }
            if(temDisciplina) {
                t.addProfessor(p);
                p.setQtdTurmas(p.getQtdTurmas()+1);
                System.out.println("Professor "+p.getNome()+" inserido na turma "+t.getCodigo()+".");
            } else throw new ErroVinculoException("O professor não ministra nenhuma disciplina da turma.");
        }
        else{
            throw new ProfessorIncompativelException();
        }
        return "Professor "+p.getNome()+" inserido na turma "+t.getCodigo()+" com sucesso.";
    }
    public String desvincularProfessorTurma(String codigo, String cpf){
        if(!pessoaMap.containsKey(cpf)) throw new PessoaNaoEncontradaException();
        if(!(pessoaMap.get(cpf) instanceof Professor p)) throw new TipoIncompativelException(pessoaMap.get(cpf).getNome(), "professor");

        Turma t = turmaMap.get(codigo);
        if(t.getProfessores().size() < 2) throw new ProfessoresInsuficientesException();

        t.removeProfessor(p);
        return "professor removido na turma "+t.getCodigo()+".";
    }
    public String vincularDisciplinaTurma(String codigoDisciplina, String codigoTurma){
        Disciplina d = disciplinaMap.get(codigoDisciplina);
        Turma t = turmaMap.get(codigoTurma);
        if(d == null) throw new DisciplinaNaoCadastradaException();
        if(t == null) throw new ValorIncompativelException("Turma não encontrada.");
        t.addDisciplina(d);
        return "Disciplina vinculada à turma com sucesso!";
    }
    public String desvincularDisciplinaTurma(String codigoDisciplina, String codigoTurma){
        Disciplina d = disciplinaMap.get(codigoDisciplina);
        Turma t = turmaMap.get(codigoTurma);
        if(d == null) throw new DisciplinaNaoCadastradaException();
        if(t == null) throw new ValorIncompativelException("Turma não encontrada.");
        t.removeDisciplina(d);
        return "Disciplina desvinculada da turma com sucesso!";
    }

    public String relatorioProfessores(){
        StringBuilder sb = new StringBuilder();
        for(Pessoa p: pessoaMap.values()){
            if(p instanceof Professor){
                sb.append(p.getRelatorio()).append("\n");
            }
        }
        return sb.toString();
    }
    public String relatorioAlunos(){
        StringBuilder sb = new StringBuilder();
        for(Pessoa p: pessoaMap.values()){
            if(p instanceof Aluno){
                sb.append(p.getRelatorio()).append("\n");
            }
        }
        return sb.toString();
    }
    public String relatorioDisciplinas(){
        StringBuilder sb = new StringBuilder();
        for(Disciplina d: disciplinaMap.values()){
            sb.append(d.getRelatorio()).append("\n");
        }
        return sb.toString();
    }
    public String relatorioTurmas(){
        StringBuilder sb = new StringBuilder();
        for(Turma t: turmaMap.values()){
            sb.append(t.getRelatorio()).append("\n");
        }
        return sb.toString();
    }
}
