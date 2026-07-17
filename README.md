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

### Cadastro

- Cadastro de usuário com dados válidos
- Geração dinâmica de e-mail para evitar duplicidade
- Cadastro com e-mail já existente (cenário negativo)


### Fluxo E2E de Compra

✔ Login
✔ Busca de produto
✔ Adição ao carrinho
✔ Validação do carrinho
✔Checkout completo com confirmação do pedido

### Checkout
- Aceitar termos de serviço
- Avançar para a página de checkou

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


## Sprint 1.6 - Checkout Completo

Nesta sprint foi implementado o fluxo completo de compra no Demo Web Shop:

- Login
- Busca de produto
- Adição ao carrinho
- Aceite dos termos de serviço
- Checkout
- Billing Address
- Shipping Address
- Shipping Method
- Payment Method
- Confirmação do pedido

### Novas classes

- CompleteCheckoutPage.java
- CompleteCheckoutSteps.java

### Melhorias

- Método reutilizável `selecionarPorTexto()` na BasePage
- Esperas explícitas em métodos reutilizáveis
- Validação da mensagem de pedido concluído

### Resultado da suíte


## Status Atual

- 8 cenários automatizados
- Selenium WebDriver
- Cucumber BDD
- JUnit 5
- Page Object Model (POM)
- Captura automática de evidências em falhas
- Fluxo E2E completo de compra validado
- Cenários positivos e negativos implementados


Tests run: 8
Failures: 0
Errors: 0
Skipped: 0
BUILD SUCCESS

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



