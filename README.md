# 🎓 Sistema de Gerenciamento de Eventos

<p align="center">
  <strong>Engenharia de Software — 2º Semestre</strong>
</p>

<p align="center">
  Sistema desktop desenvolvido em Java para gerenciamento de eventos acadêmicos, participantes e inscrições.
</p>

---

## 📌 Sobre o Projeto

O **Sistema de Gerenciamento de Eventos** é uma aplicação desktop desenvolvida como parte de uma atividade acadêmica do curso de Engenharia de Software.

O objetivo é facilitar a organização de eventos acadêmicos, permitindo cadastrar eventos, gerenciar participantes, realizar inscrições e acompanhar a disponibilidade de vagas.

O projeto utiliza a linguagem Java e a biblioteca Swing para construção da interface gráfica, seguindo o padrão arquitetural MVC (Model–View–Controller).

## 🎯 Objetivos

- Facilitar o gerenciamento de eventos acadêmicos.
- Organizar o cadastro de participantes.
- Controlar as inscrições realizadas.
- Evitar inscrições duplicadas.
- Monitorar a capacidade e a disponibilidade de vagas.
- Gerar relatórios com informações dos eventos.

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Utilização |
|---|---|
| Java | Linguagem de programação |
| Java Swing | Desenvolvimento da interface gráfica |
| ArrayList | Armazenamento dos registros em memória |
| JTable | Exibição de dados em tabelas |
| MVC | Organização e arquitetura do sistema |
| Visual Studio Code | Ambiente de desenvolvimento |

## ⚙️ Funcionalidades

### 📅 Gerenciamento de Eventos
- Cadastro de eventos.
- Informações de código, nome, data, local e capacidade máxima.
- Listagem de eventos cadastrados.
- Alteração da capacidade dos eventos.

### 👥 Gerenciamento de Participantes
- Cadastro de participantes.
- Registro de código, nome e contato.
- Listagem dos participantes cadastrados.

### 📝 Gerenciamento de Inscrições
- Inscrição de participantes em eventos.
- Verificação de inscrições duplicadas.
- Controle automático de vagas disponíveis.
- Cancelamento de inscrições.

### 📊 Relatórios
- Consulta de participantes inscritos.
- Quantidade total de inscrições por evento.
- Capacidade máxima.
- Quantidade de vagas disponíveis.

## 🏗️ Arquitetura do Projeto

O sistema utiliza a arquitetura **MVC (Model–View–Controller)**, dividindo as responsabilidades em três camadas:

- **Model:** representa os dados e as entidades do sistema.
- **View:** contém as telas e os componentes gráficos desenvolvidos com Java Swing.
- **Controller:** controla as operações, validações e regras de negócio.

### 📂 Estrutura de Pastas

```text
SistemaGerenciamentoEventos/
│
└── src/
    └── br/
        └── com/
            └── eventos/
                │
                ├── model/
                │   ├── Evento.java
                │   ├── Participante.java
                │   └── Inscricao.java
                │
                ├── view/
                │   ├── TelaPrincipal.java
                │   ├── TelaEventos.java
                │   ├── TelaParticipantes.java
                │   ├── TelaInscricoes.java
                │   └── TelaRelatorios.java
                │
                ├── controller/
                │   ├── EventoController.java
                │   ├── ParticipanteController.java
                │   └── InscricaoController.java
                │
                └── Main.java
```

## ▶️ Como Executar o Projeto

### Pré-requisitos

- Java JDK 21 ou superior.
- Visual Studio Code.
- Extension Pack for Java instalado no VS Code.

### Executando

1. Clone o repositório:

```bash
git clone URL_DO_REPOSITORIO
```

2. Abra a pasta do projeto no Visual Studio Code.

3. Verifique se todas as extensões Java necessárias estão instaladas.

4. Localize o arquivo `Main.java` dentro de:

```text
src/br/com/eventos/Main.java
```

5. Clique em **Run** para iniciar a aplicação.

## 📋 Regras de Negócio

O sistema possui algumas regras para garantir o funcionamento correto:

- Não permitir códigos duplicados para eventos e participantes.
- Não permitir capacidades negativas.
- Não permitir inscrições duplicadas do mesmo participante no mesmo evento.
- Impedir inscrições quando não houver vagas disponíveis.
- Não permitir reduzir a capacidade abaixo da quantidade de participantes inscritos.
- Liberar a vaga após o cancelamento de uma inscrição.

## 💾 Armazenamento de Dados

Os registros são armazenados em memória utilizando `ArrayList`, conforme as orientações da atividade acadêmica.

**Observação:** os dados cadastrados são mantidos somente durante a execução do programa. Ao fechar a aplicação, os registros são perdidos.

## 👨‍💻 Desenvolvido por

**Grupo de Engenharia de Software — 2º Semestre**

Integrantes:
- Davi Santana Alves Alecrim
- Ian Victor Viana de Jesus
- Icaro Ruan Viana de Jesus
- Lucas Gabriel Alves de Souza
- Luiza Silva Freitas Hortelão

## 🧑‍🏫 **Orientador**
- Hudson Neves

## 🎓 Projeto Acadêmico

Desenvolvido para a disciplina de Engenharia de Software, com o propósito de aplicar conceitos de programação orientada a objetos, arquitetura MVC, interfaces gráficas e regras de negócio utilizando Java.

---

<p align="center">
  <strong>Desenvolvido com ☕ Java e dedicação!</strong>
</p>
