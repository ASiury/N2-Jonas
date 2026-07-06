# Sistema de Controle Escolar (N2-Jonas)

Bem-vindo ao **Sistema de Controle Escolar**, um programa desenvolvido em Java para auxiliar no gerenciamento das principais entidades de uma instituição de ensino.

Este documento serve como guia completo para a execução e utilização das funcionalidades do sistema.

## Índice
1. [Visão Geral](#visão-geral)
2. [Como Executar o Sistema](#como-executar-o-sistema)
3. [Como Utilizar (Menus e Funcionalidades)](#como-utilizar)
    - [1. Gerenciar Corpo Docente (Professores)](#1-gerenciar-corpo-docente-professores)
    - [2. Gerenciar Corpo Discente (Alunos)](#2-gerenciar-corpo-discente-alunos)
    - [3. Gerenciar Disciplinas](#3-gerenciar-disciplinas)
    - [4. Gerenciar Turmas](#4-gerenciar-turmas)

---

## Visão Geral
O sistema opera através do console (terminal) e utiliza um menu interativo numérico. Através dele, a secretaria ou o administrador escolar pode realizar cadastros, remover registros, gerar relatórios e vincular as entidades (por exemplo, matricular alunos em turmas ou atribuir disciplinas a professores).

---

## Como Executar o Sistema

O sistema é um projeto Java padrão sem dependências externas (como Maven ou Gradle), sendo bastante simples de executar.

###  Utilizando uma IDE (Recomendado)
O projeto já conta com os arquivos de configuração do IntelliJ IDEA, o que facilita muito o processo.

## 📖 Como Utilizar

Após iniciar o programa, você verá o Menu Principal. Para navegar, basta digitar o **número** correspondente à opção desejada e pressionar `Enter`.

Programa de controle escolar
----------------------------
1 - Gerenciar corpo docente
2 - Gerenciar corpo discente
3 - Gerenciar disciplinas
4 - Gerenciar turmas
0 - Fechar programa

### 1. Gerenciar Corpo Docente (Professores)
Ao selecionar a opção `1`, você terá acesso ao menu de professores.
- **1) Cadastrar professor:** Permite inserir um novo professor. Se a formação for "Pedagogia", será cadastrado como Professor Polivalente. Caso contrário, será solicitado o código da disciplina para cadastro como Especialista.
- **2) Remover professor:** Exclui um professor pelo seu CPF.
- **3) Alterar disciplina ministrada:** Permite trocar a disciplina vinculada a um professor (busca por CPF).
- **4) Relatório dos professores:** Lista todos os professores cadastrados.
- **0) Voltar:** Retorna ao Menu Principal.

### 2. Gerenciar Corpo Discente (Alunos)
Ao selecionar a opção `2`, você gerenciará os alunos matriculados.
- **1) Cadastrar aluno:** Requer o nome, CPF e a série escolar do aluno.
- **2) Remover aluno:** Exclui o aluno utilizando o CPF.
- **3) Atribuir notas:** Permite informar o CPF do aluno e laçar as notas, informando o código da disciplina seguida pela nota (digite '0' no código da disciplina para concluir o laçamento).
- **4) Alterar situação de matrícula:** Ative ('1') ou tranque ('2') a matrícula de um aluno fornecendo seu CPF.
- **5) Relatório de alunos:** Lista todos os estudantes registrados.
- **0) Voltar:** Retorna ao Menu Principal.

### 3. Gerenciar Disciplinas
A opção `3` foca na grade curricular da escola.
- **1) Cadastrar disciplina:** Informe o nome, o código de identificação único e a carga horária (que deve ser de, no mínimo, 30 horas).
- **2) Remover disciplina:** Exclui uma disciplina buscando por seu código.
- **3) Relatório de disciplinas:** Exibe as disciplinas cadastradas no sistema.
- **0) Voltar:** Retorna ao Menu Principal.

### 4. Gerenciar Turmas
Com a opção `4`, você conectará alunos, professores e disciplinas.
- **1) Cadastrar turma:** Crie uma turma com código, ano, sala e turno (M - Matutino / V - Vespertino). Serão solicitados também o código da disciplina vinculada à turma.
- **2) Remover turma:** Exclui a turma através do seu código.
- **3/4) Inserir / Remover aluno:** Vincula ou desvincula um aluno de uma turma usando os respectivos CPFs e códigos.
- **5/6) Inserir / Remover professor:** Adiciona ou retira professores responsáveis pelas turmas.
- **7/8) Inserir / Remover disciplina:** Adiciona ou retira disciplinas da grade daquela turma específica.
- **9) Relatório de turmas:** Mostra os dados consolidados das turmas formadas.
- **0) Voltar:** Retorna ao Menu Principal.
