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