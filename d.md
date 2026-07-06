## 4. Diagrama de Classes


```mermaid
classDiagram
    class Pessoa {
        <<abstract>>
        -String nome
        -String cpf
        +Pessoa(String nome, String cpf)
        +getNome() String
        +getCpf() String
        +getRelatorio()* String
    }


    class Aluno {
        -String matricula
        -int serie
        -StatusMatricula statusMatricula
        -HashMap~Disciplina, Double~ notas
        +Aluno(String nome, String cpf, int serie)
        +setStatus(byte num) void
        +getSerie() int
        +getMatricula() String
        +getStatus() StatusMatricula
        +getNotas() HashMap~Disciplina, Double~
        +setNotas(Disciplina d, double nota) void
        +getRelatorio() String
    }


    class Professor {
        <<abstract>>
        -double salarioBase
        -String formacao
        -int qtdTurmas
        +Professor(String nome, String cpf, String disciplina)
        +calcularSalario()* double
        +getFormacao() String
        +getQtdTurmas() int
        +setQtdTurmas(int) void
    }


    class ProfessorEspecialista {
        -Disciplina disciplina
        +ProfessorEspecialista(String nome, String cpf, String formacao, Disciplina disciplina)
        +calcularSalario() double
        +getDisciplina() Disciplina
        +setarDisciplinas(Disciplina d) void
        +getRelatorio() String
    }


    class ProfessorPolivalente {
        +ProfessorPolivalente(String nome, String cpf, String formacao)
        +calcularSalario() double
        +getRelatorio() String
    }


    class Disciplina {
        -String nome
        -String codigo
        -int cargaHoraria
        +Disciplina(String nome, String codigo, int cargaHoraria)
        +getNome() String
        +getCodigo() String
        +getCargaHoraria() int
        +getRelatorio() StringBuilder
    }


    class Turma {
        -String codigo
        -int anoEscolar
        -char turno
        -ArrayList~String~ salas
        -ArrayList~Disciplina~ disciplinas
        -ArrayList~Professor~ professores
        -ArrayList~Aluno~ alunos
        +Turma(String codigo, int anoEscolar, String sala, char turno, Disciplina disciplina)
        +addAluno(Aluno a) void
        +addProfessor(Professor p) void
        +addDisciplina(Disciplina d) void
        +getRelatorio() StringBuilder
    }


    class StatusMatricula {
        <<enumeration>>
        CADASTRO
        TRANCADO
        ATIVO
    }


    Pessoa <|-- Aluno
    Pessoa <|-- Professor
    Professor <|-- ProfessorEspecialista
    Professor <|-- ProfessorPolivalente
   
    Aluno "1" --> "1" StatusMatricula : possui
    Aluno "1" --> "0..*" Disciplina : notas
    ProfessorEspecialista "1" --> "1" Disciplina : leciona
   
    Turma "1" *-- "0..*" Aluno : contém
    Turma "1" *-- "0..*" Professor : contém
    Turma "1" *-- "1..*" Disciplina : contém
```


---


## 5. Diagrama de Máquina de Estados (Status da Matrícula do Aluno)


Este diagrama representa as transições do ciclo de vida da matrícula de um Aluno dentro da escola:


```mermaid
stateDiagram-v2
    [*] --> CADASTRO : Novo Aluno Criado
   
    CADASTRO --> ATIVO : setStatus(1) [Ativar]
    CADASTRO --> TRANCADO : setStatus(2) [Trancar]
   
    ATIVO --> TRANCADO : setStatus(2) [Trancar]
    TRANCADO --> ATIVO : setStatus(1) [Reativar]
   
    ATIVO --> [*] : Remover Aluno
    TRANCADO --> [*] : Remover Aluno
    CADASTRO --> [*] : Remover Aluno
```




