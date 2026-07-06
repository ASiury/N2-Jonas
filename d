# Documentação do Sistema Escolar


## 1. Descrição Geral do Problema e da Solução


**Problema:**
Uma instituição de ensino necessita de um sistema para gerenciar suas rotinas acadêmicas. O processo manual ou descentralizado de controle de alunos, professores, disciplinas e turmas gera inconsistências, como conflitos de alocação de salas, atribuição incorreta de professores (especialistas vs. polivalentes) e dificuldade no acompanhamento do status de matrícula e notas dos alunos.


**Solução:**
Foi desenvolvido um sistema em Java utilizando os princípios da Programação Orientada a Objetos (POO) com uma arquitetura baseada em MVC (Model-View-Controller). O sistema centraliza o cadastro de pessoas (Alunos e Professores), disciplinas e turmas. O controle das regras de negócio (como a validação de vinculação de professores por série, limite de professores por turma, restrição de salas e turnos) é gerido pelo `EscolaController`, garantindo a integridade dos dados e prevenindo conflitos operacionais.


---


## 2. Requisitos Funcionais


Abaixo estão os principais requisitos funcionais do sistema:


*   **RF01 - Gerenciar Alunos:** O sistema deve permitir cadastrar, remover e consultar alunos, armazenando dados como nome, CPF, matrícula (gerada automaticamente) e série.
*   **RF02 - Gerenciar Professores:** O sistema deve permitir cadastrar professores, diferenciando-os entre Especialistas (para turmas de 6º ao 9º ano, associados a uma disciplina específica) e Polivalentes (para turmas do 1º ao 5º ano, com formação restrita a "Pedagogia").
*   **RF03 - Gerenciar Turmas e Disciplinas:** O sistema deve permitir criar disciplinas (com código e carga horária) e turmas (com código, ano escolar, sala, turno e disciplinas vinculadas), além de validar a disponibilidade da sala no turno especificado.
*   **RF04 - Vincular Entidades:** O sistema deve permitir matricular alunos em turmas, atribuir professores a turmas (respeitando limites e especialidades) e atribuir notas às disciplinas cursadas pelos alunos.
*   **RF05 - Alterar Status da Matrícula:** O sistema deve permitir que o status da matrícula do aluno seja alterado entre "Cadastro" (padrão inicial), "Ativo" e "Trancado".


---


## 3. Casos de Uso


Abaixo estão as descrições textuais dos quatro fluxos principais do sistema:


### UC01: Cadastrar Aluno
*   **Ator:** Administrador
*   **Fluxo Principal:**
    1. O Administrador insere o nome, CPF e a série do aluno.
    2. O sistema verifica se o CPF já está cadastrado.
    3. O sistema gera um número de matrícula único.
    4. O sistema salva o aluno com o status de matrícula inicial ("CADASTRO").
    5. O sistema exibe uma mensagem de sucesso.
*   **Fluxo de Exceção:** Se o CPF já existir, o sistema lança uma `ConflictException`.


### UC02: Criar Turma
*   **Ator:** Administrador
*   **Fluxo Principal:**
    1. O Administrador informa o ano escolar, código da turma, sala, turno e o código da disciplina inicial.
    2. O sistema verifica se a disciplina existe.
    3. O sistema verifica se a sala já está ocupada por outra turma no mesmo turno.
    4. O sistema cria a turma e a salva.
    5. O sistema exibe mensagem de sucesso.
*   **Fluxo de Exceção:** Se a sala e o turno já estiverem em uso, o sistema lança uma `SalaIndisponivelException`.


### UC03: Vincular Professor à Turma
*   **Ator:** Administrador
*   **Fluxo Principal:**
    1. O Administrador informa o código da turma e o CPF do professor.
    2. O sistema recupera a turma e o professor.
    3. Se a turma for do 1º ao 5º ano, o sistema verifica se o professor é Polivalente e se a turma já possui um professor (limite de 1).
    4. Se a turma for do 6º ao 9º ano, o sistema verifica se o professor é Especialista, se a sua disciplina está na turma e se ele ainda não foi inserido.
    5. O sistema adiciona o professor à turma e incrementa sua quantidade de turmas.
*   **Fluxo de Exceção:** Se houver incompatibilidade de nível de ensino ou limite atingido, o sistema lança uma `TipoIncompativelException` ou `LimiteProfessorAtingidoException`.


### UC04: Atualizar Status de Matrícula
*   **Ator:** Administrador
*   **Fluxo Principal:**
    1. O Administrador informa o CPF do aluno e o código do novo status (1 para Ativo, 2 para Trancado).
    2. O sistema busca o aluno.
    3. O sistema atualiza o status de matrícula conforme a escolha.
    4. O sistema exibe uma mensagem de sucesso ("A matricula do aluno [Nome] foi [ativada/trancada] com sucesso.").


---


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




