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

    public EscolaController() {
        DadosPreCarregados();
    }

    private void DadosPreCarregados() {
        try {
            cadastrarDisciplina("Matemática", "MAT01", 60);
            cadastrarDisciplina("Língua Portuguesa", "POR01", 60);
            
            cadastrarAluno("João Silva", "111.111.111-11", 6);
            cadastrarAluno("Maria Santos", "222.222.222-22", 6);
            
            cadastrarProfessorEspecialista("Carlos Andrade", "333.333.333-33", "Licenciatura em Matemática", "MAT01");
            cadastrarProfessorPolivalente("Ana Paula", "444.444.444-44", "Pedagogia");
            
            cadastrarTurma(6, "T01", "Sala 1", "MAT01", 'M');
            vincularDisciplinaTurma("POR01", "T01");
            vincularDisciplinaTurma("MAT01", "T01");
            vincularAlunoTurma("T01", "111.111.111-11");
            vincularAlunoTurma("T01", "222.222.222-22");
            vincularProfessorTurma("T01", "333.333.333-33");
            
        } catch (Exception e) {
            System.out.println("Aviso: Falha ao carregar dados pré-cadastrados - " + e.getMessage());
        }
    }

    public void verificarSistema(String cpf){
        if (cpf == null) {
            throw new InvalidInputException("O CPF não pode ser nulo");
        }
        if (pessoaMap.containsKey(cpf)) {
            throw new ConflictException("O CPF", cpf, "o");
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
            throw new InvalidInputException("O CPF não pode ser nulo");
        }
        Pessoa pessoa = pessoaMap.get(cpf);
        if (pessoa == null) {
            throw new NotFoundException("Pessoa não encontrada");
        }
        if (!(pessoa instanceof Aluno)) {
            throw new TipoIncompativelException(pessoaMap.get(cpf).getNome() + " não é um aluno");
        }
        pessoaMap.remove(cpf);
        return "Aluno removido com sucesso!";
    }
    public String setNotas(String cpf, String codigo, double nota) {
        if (cpf == null || codigo == null) {
            throw new InvalidInputException("O CPF e o código não podem ser nulos");
        }
        Pessoa p = pessoaMap.get(cpf);
        if (!(p instanceof Aluno a)) {
            throw new TipoIncompativelException("Vínculo inválido");
        }

        boolean disciplinaEncontrada = false;
        Disciplina d = disciplinaMap.get(codigo);
        if (d == null) throw new NotFoundException("Disciplina não encontrada");
        
        for(Turma t: turmaMap.values()){
            if(t.hasAluno(a) && t.hasDisciplina(d)){
                disciplinaEncontrada = true;
                break;
            }
        }
        if(!disciplinaEncontrada){
            throw new InvalidInputException("Disciplina não compatível com o aluno");
        }

        a.setNotas(d, nota);
        return "Você definiu a nota do aluno " + a.getNome() + " como " + nota + " na disciplina " + d.getNome() + ".";
    }
    public String setStatusMatricula(String cpf, byte num) {
        if (!(pessoaMap.get(cpf) instanceof Aluno a)) throw new InvalidInputException("CPF inválido ou não é um aluno");

        String s = "";
        if (num == 1) s = "ativada";
        else if (num == 2) s = "trancada";
        a.setStatus(num);
        return "A matricula do aluno " + a.getNome() + " foi " + s + " com sucesso.";
    }

    public String removerProfessor(String cpf) {
        if (cpf == null) {
            throw new InvalidInputException("O CPF não pode ser nulo");
        }
        Pessoa pessoa = pessoaMap.get(cpf);
        if (pessoa == null) {
            throw new PessoaNaoEncontradaException();
        }
        if (!(pessoa instanceof Professor)) {
            throw new TipoIncompativelException(pessoaMap.get(cpf).getNome() + " não é um professor");
        }
        pessoaMap.remove(cpf);
        return "Professor removido com sucesso!";
    }
    public String cadastrarProfessorPolivalente(String nome, String cpf, String formacao){
        verificarSistema(cpf);
        Professor p = new ProfessorPolivalente(nome, cpf, formacao);
        if (!Objects.equals(formacao, "Pedagogia")) {
            throw new TipoIncompativelException("Professor tem formação incompatível");
        }
        pessoaMap.put(cpf, p);
        return "Professor cadastrado com sucesso!";
    }
    public String cadastrarProfessorEspecialista(String nome, String cpf, String formacao, String codigoDisciplina){
        Disciplina d = disciplinaMap.get(codigoDisciplina);
        if(d == null) throw new NotFoundException("Disciplina não cadastrada");
        verificarSistema(cpf);
        Professor p = new ProfessorEspecialista(nome, cpf, formacao, d);
        if (Objects.equals(formacao, "Pedagogia")) {
           throw new TipoIncompativelException("Professor tem formação incompatível");
        }
        pessoaMap.put(cpf, p);
        return "Professor cadastrado com sucesso!";
    }
    public String setarDisciplinaProfessor(String cpf, String code){
        if(code == null || cpf == null) {
            throw new InvalidInputException("O código ou CPF não podem ser nulos");
        }
        Professor p = (Professor) pessoaMap.get(cpf);
        Disciplina d = disciplinaMap.get(code);
        if(p == null) throw new PessoaNaoEncontradaException();
        if(d == null) throw new NotFoundException("Disciplina não encontrada");
        if(!(p instanceof ProfessorEspecialista)) throw new TipoIncompativelException("Professor não é especialista");
        ((ProfessorEspecialista) p).setarDisciplinas(d);
        return "Disciplina "+d.getNome()+" foi atribuída para "+p.getNome()+" com sucesso!";
    }

    public String removerDisciplina(String codigo){
        if (codigo == null) throw new InvalidInputException("O código não pode ser nulo");
        Disciplina d = disciplinaMap.get(codigo);
        if (d == null) throw new NotFoundException("Disciplina não encontrada");
        String nome =  d.getNome();
        disciplinaMap.remove(codigo);

        return "Disciplina "+nome+" removida do sistema.";
    }
    public String cadastrarDisciplina(String nome, String codigo, int cargaHoraria){
        if (codigo == null) throw new InvalidInputException("O código não pode ser nulo");
        if (disciplinaMap.get(codigo) != null) throw new ConflictException("Disciplina " + codigo + " já foi cadastrado no sistema.");
        Disciplina d = new Disciplina(nome, codigo, cargaHoraria);
        disciplinaMap.put(codigo, d);
        return "Disciplina "+d.getNome()+" criada com sucesso!";
    }

    public String cadastrarTurma(int anoEscolar, String codigo, String sala, String codigoDisciplina, char turno) {
        if (codigo == null || codigoDisciplina == null || sala == null) {
            throw new InvalidInputException("O código, código da disciplina ou sala não podem ser nulos");
        }
        Disciplina d = disciplinaMap.get(codigoDisciplina);
        if (d == null) throw new NotFoundException("Disciplina não encontrada");

        if (turmaMap.containsKey(codigo)) {
            throw new ConflictException("A turma de código", codigo, "a");
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
            throw new InvalidInputException("codigo cannot be null");
        }
        Turma t = turmaMap.remove(codigo);
        if (t != null) {
            return "Turma removida com sucesso!";
        } else {
            return "Erro: Turma não encontrada no sistema.";
        }
    }
    public String vincularAlunoTurma(String codigo, String cpf){
        if(!pessoaMap.containsKey(cpf)) throw new NotFoundException("Aluno não matriculado.");
        if(!(pessoaMap.get(cpf) instanceof Aluno a)) throw new TipoIncompativelException(pessoaMap.get(cpf).getNome() + " não é aluno");
        Turma t = turmaMap.get(codigo);
        if(t == null) throw new NotFoundException("Turma não encontrada.");

        if(a.getSerie() != t.getAnoEscolar()) throw new TipoIncompativelException("Ano de estudo incompatível.");

        t.addAluno(a);
        return "Aluno inserido na turma "+t.getCodigo()+".";
    }
    public String desvincularAlunoTurma(String codigo, String cpf){
        if(!pessoaMap.containsKey(cpf)) throw new NotFoundException("Pessoa não encontrada");
        if(!(pessoaMap.get(cpf) instanceof Aluno a)) throw new TipoIncompativelException(pessoaMap.get(cpf).getNome()+ " não é um aluno");

        Turma t = turmaMap.get(codigo);
        if(t == null) throw new NotFoundException("Turma não encontrada.");
       
        t.removeAluno(a);
        return "Aluno removido na turma "+t.getCodigo()+".";
    }
    public String vincularProfessorTurma(String codigo, String cpf){
        Turma t = turmaMap.get(codigo);
        if(t == null) throw new ValorIncompativelException("Turma não encontrada.");
        Pessoa pessoa = pessoaMap.get(cpf);
        if (pessoa == null) throw new NotFoundException("Professor não encontrado");
        if (!(pessoa instanceof Professor p)) throw new TipoIncompativelException("O CPF informado não pertence a um professor.");
        if(t.getAnoEscolar() >= 1 && t.getAnoEscolar() <= 5 ){
            if(!(p instanceof ProfessorPolivalente)) throw new TipoIncompativelException("Polivalente não pode ser vinculado");
            if(t.getProfessores().size() == 1) throw new LimiteProfessorAtingidoException();
            t.addProfessor(p);
            p.setQtdTurmas(p.getQtdTurmas()+1);
        }
        else if(t.getAnoEscolar() >= 6 && t.getAnoEscolar() <= 9 ){
            if(!(p instanceof ProfessorEspecialista)) throw new TipoIncompativelException("Especialista não pode ser vinculado");
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
            } else throw new TipoIncompativelException("O professor não ministra nenhuma disciplina da turma.");
        }
        else{
            throw new TipoIncompativelException("Professor incompatível com a turma");
        }
        return "Professor "+p.getNome()+" inserido na turma "+t.getCodigo()+" com sucesso.";
    }
    public String desvincularProfessorTurma(String codigo, String cpf){
        if(!pessoaMap.containsKey(cpf)) throw new NotFoundException("Pessoa não encontrada");
        if(!(pessoaMap.get(cpf) instanceof Professor p)) throw new TipoIncompativelException(pessoaMap.get(cpf).getNome() + " não é professor");

        Turma t = turmaMap.get(codigo);
        if(t == null) throw new ValorIncompativelException("Turma não encontrada.");
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
        if(d == null) throw new NotFoundException("Disciplina não encontrada");
        if(t == null) throw new NotFoundException("Turma não encontrada.");
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
