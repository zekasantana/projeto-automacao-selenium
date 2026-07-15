# Projeto de Automação Selenium

Framework de automação de testes Web desenvolvido com Java, Selenium WebDriver, Cucumber BDD, JUnit 5 e Maven.

## Objetivo

Este projeto tem como objetivo demonstrar a construção de uma arquitetura profissional de automação de testes, utilizando Page Object Model, separação de responsabilidades e cenários escritos em Gherkin.

## Tecnologias utilizadas

- Java 17
- Selenium WebDriver
- Cucumber
- JUnit 5
- Maven
- IntelliJ IDEA
- Git
- GitHub


### Cenários Automatizados

- Login com sucesso
- Login inválido
- Logout com sucesso
- Captura automática de evidências em falhas


## Captura de Evidências

O framework possui captura automática de screenshots quando um cenário falha.

### Localização

As evidências são armazenadas na pasta:

evidencias/

### Funcionamento

A captura é executada automaticamente através de Hooks do Cucumber utilizando:

- Selenium TakesScreenshot
- Apache Commons IO
- Hook @After

### Benefícios

- Facilita análise de falhas
- Auxilia depuração
- Gera evidências para relatórios
- Prática utilizada em projetos corporativos

## Changelog

### v1.0.0 - Fundação do Framework
- Estrutura inicial do framework
- DriverFactory
- BasePage
- Hooks
- Login com sucesso
- Login inválido

### Sprint 1.1
- Captura automática de screenshots em falhas

### Sprint 1.2
- Logout com sucesso


## Arquitetura do projeto

```text
src
├── main
│   └── java
│       └── br.com.ezequias.automacao
│           ├── factory
│           ├── pages
│           └── utils
│
└── test
    ├── java
    │   └── br.com.ezequias.automacao
    │       ├── hooks
    │       ├── runner
    │       └── stepdefinitions
    │
    └── resources
        └── features



