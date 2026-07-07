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

    public void verificarCpf(String cpf){
        if (cpf == null) {
            throw new InvalidInputException("O CPF não pode ser nulo");
        }
        if (pessoaMap.containsKey(cpf)) {
            throw new ConflictException("O CPF", cpf, "o");
        }
    }
    public void verificarTurma(String codigo){
        if (codigo == null) {
            throw new InvalidInputException("O código de turma não pode ser nulo");
        }
        if (turmaMap.containsKey(codigo)) {
            throw new ConflictException("A turma de código", codigo, "a");
        }
    }
    public void verificarDisciplina(String codigo){
        if (codigo == null) {
            throw new InvalidInputException("O código de disciplina não pode ser nulo");
        }
        if (disciplinaMap.containsKey(codigo)) {
            throw new ConflictException("A disciplina de código", codigo, "a");
        }
    }

    public Pessoa existePessoa(String cpf){
        if (cpf == null) throw new InvalidInputException("O CPF não pode ser nulo");
        if (!pessoaMap.containsKey(cpf)) throw new NotFoundException("Este CPF não está cadastrado no sistema");
        return pessoaMap.get(cpf);
    }
    public Disciplina existeDisciplina(String codigo){
        if(codigo == null) throw new InvalidInputException("O código de disciplina não pode ser nulo");
        if(!disciplinaMap.containsKey(codigo)) throw new NotFoundException("Esta disciplina não está cadastrada no sistema");
        return disciplinaMap.get(codigo);
    }
    public Turma existeTurma(String codigo){
        if(codigo == null) throw new InvalidInputException("O código de turma não pode ser nulo");
        if(!turmaMap.containsKey(codigo)) throw new NotFoundException("Esta turma não está cadastrada no sistema");
        return turmaMap.get(codigo);
    }

    public String cadastrarAluno(String nome, String cpf, int serie){
        verificarCpf(cpf);
        Aluno a = new Aluno(nome, cpf, serie);
        pessoaMap.put(cpf, a);
        return "Aluno cadastrado com sucesso!";
    }
    public String removerAluno(String cpf) {
        Pessoa p = existePessoa(cpf);
        if (!(p instanceof Aluno)) {
            throw new TipoIncompativelException(pessoaMap.get(cpf).getNome() + " não é um aluno");
        }
        pessoaMap.remove(cpf);
        return "Aluno removido com sucesso!";
    }
    public String setNotas(String cpf, String codigo, double nota) {
        Pessoa p = existePessoa(cpf);
        if (!(p instanceof Aluno a)) {
            throw new TipoIncompativelException("Vínculo inválido");
        }
        boolean disciplinaEncontrada = false;
        Disciplina d = existeDisciplina(codigo);
        
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
        Pessoa p = existePessoa(cpf);
        if (!(p instanceof Aluno a)) throw new InvalidInputException("CPF não pertence a um aluno");

        String s = "";
        if (num == 1) s = "ativada";
        else if (num == 2) s = "trancada";
        a.setStatus(num);
        return "A matricula do aluno " + a.getNome() + " foi " + s + " com sucesso.";
    }

    public String removerProfessor(String cpf) {
        Pessoa p = existePessoa(cpf);
        if (!(p instanceof Professor)) throw new TipoIncompativelException(pessoaMap.get(cpf).getNome() + " não é um professor");
        pessoaMap.remove(cpf);
        return "Professor removido com sucesso!";
    }
    public String cadastrarProfessorPolivalente(String nome, String cpf, String formacao){
        verificarCpf(cpf);
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
        verificarCpf(cpf);
        Professor p = new ProfessorEspecialista(nome, cpf, formacao, d);
        if (Objects.equals(formacao, "Pedagogia")) {
           throw new TipoIncompativelException("Professor tem formação incompatível");
        }
        pessoaMap.put(cpf, p);
        return "Professor cadastrado com sucesso!";
    }
    public String setarDisciplinaProfessor(String cpf, String code){
        Pessoa p = existePessoa(cpf);
        Disciplina d = existeDisciplina(code);
        if(!(p instanceof ProfessorEspecialista)) throw new TipoIncompativelException("Professor não é especialista");
        ((ProfessorEspecialista) p).setarDisciplinas(d);
        return "Disciplina "+d.getNome()+" foi atribuída para "+p.getNome()+" com sucesso!";
    }

    public String removerDisciplina(String codigo){
        Disciplina d = existeDisciplina(codigo);
        disciplinaMap.remove(codigo);
        return "Disciplina "+d.getNome()+" removida do sistema.";
    }
    public String cadastrarDisciplina(String nome, String codigo, int cargaHoraria){
        verificarDisciplina(codigo);
        Disciplina d = new Disciplina(nome, codigo, cargaHoraria);
        disciplinaMap.put(codigo, d);
        return "Disciplina "+d.getNome()+" criada com sucesso!";
    }

    public String cadastrarTurma(int anoEscolar, String codigo, String sala, String codigoDisciplina, char turno) {
        if (sala == null) {
            throw new InvalidInputException("Sala não deve ser nulo");
        }
        verificarTurma(codigo);
        Disciplina d = existeDisciplina(codigoDisciplina);

        for(Turma t: turmaMap.values()){
            for(int i = 0; i < t.getSalas().size(); i++){
                if(t.getSalas().get(i).equals(sala) && t.getTurno().equals(turno)) {
                    throw new SalaIndisponivelException("Esta turma não pode estudar nesta sala no turno especificado");
                }
            }
        }
        Turma t = new Turma(codigo, anoEscolar, sala, turno, d);
        turmaMap.put(codigo, t);
        return "Nova turma "+t.getCodigo()+" criada com sucesso!";
    }
    public String removerTurma(String codigo) {
        Turma t = existeTurma(codigo);
        turmaMap.remove(codigo);
        return "Turma de código "+ t.getCodigo() +"removida com sucesso!";
    }
    public String vincularAlunoTurma(String codigo, String cpf){
        Pessoa p = existePessoa(cpf);
        if(!(p instanceof Aluno a)) throw new TipoIncompativelException(pessoaMap.get(cpf).getNome() + " não é aluno");
        Turma t = existeTurma(codigo);
        if(a.getSerie() != t.getAnoEscolar()) throw new TipoIncompativelException("Ano de estudo incompatível.");
        t.addAluno(a);
        return "Aluno "+ a.getNome()+" inserido na turma "+t.getCodigo()+".";
    }
    public String desvincularAlunoTurma(String codigo, String cpf){
        Pessoa p = existePessoa(cpf);
        if(!(p instanceof Aluno a)) throw new TipoIncompativelException(pessoaMap.get(cpf).getNome()+ " não é um aluno");
        Turma t = existeTurma(codigo);
        boolean encontrou = false;
        for(Aluno a1 : t.getAlunos()){
            if(a1.getCpf().equals(cpf)){
                encontrou = true;
                break;
            }
        }
        if(!encontrou) {
            throw new NaoInseridoTurmaException("O aluno "+a.getNome()+" não faz parte desta turma");
        }
        t.removeAluno(a);
        return "Aluno "+a.getNome()+" removido na turma "+t.getCodigo()+".";
    }
    public String vincularProfessorTurma(String codigo, String cpf){
        Turma t = existeTurma(codigo);
        Pessoa pessoa = existePessoa(cpf);
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
        Pessoa pessoa = existePessoa(cpf);
        if(!(pessoa instanceof Professor p)) throw new TipoIncompativelException(pessoa.getNome() + " não é professor");
        Turma t = existeTurma(codigo);
        if(t.getProfessores().size() == 1) throw new ProfessoresInsuficientesException();
        boolean encontrou = false;
        for(Professor p1 : t.getProfessores()){
            if(p1.getCpf().equals(cpf)){
                encontrou = true;
                break;
            }
        }
        if(!encontrou){
            throw new NaoInseridoTurmaException("O professor "+ p.getNome()+" não ministra aulas nesta turma");
        }
        t.removeProfessor(p);
        return "professor removido na turma "+t.getCodigo()+".";
    }
    public String vincularDisciplinaTurma(String codigoDisciplina, String codigoTurma){
        Disciplina d = existeDisciplina(codigoDisciplina);
        Turma t = existeTurma(codigoTurma);
        t.addDisciplina(d);
        return "Disciplina "+d.getNome()+" vinculada à turma de código"+ t.getCodigo() +" com sucesso!";
    }
    public String desvincularDisciplinaTurma(String codigoDisciplina, String codigoTurma){
        Disciplina d = existeDisciplina(codigoDisciplina);
        Turma t = existeTurma(codigoTurma);
        boolean encontrou = false;
        for(Disciplina d1 : t.getDisciplinas()){
            if(d1.getCodigo().equals(d.getCodigo())){
                encontrou = true;
                break;
            }
        }
        if(!encontrou){
            throw new NaoInseridoTurmaException("A disciplina "+d.getNome()+" não faz parte da grade curricular desta turma");
        }
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
