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
- GitHub Actions
- Docker


### Cenários Automatizados

- Login com sucesso
- Logout com sucesso
- Captura automática de evidências em falhas

### Login negativo

- Login com credenciais inválidas
- Login com campos de e-mail e senha vazios
- Validação da mensagem de erro apresentada ao usuário

### Cadastro

- Cadastro de usuário com dados válidos
- Geração dinâmica de e-mail para evitar duplicidade
- Cadastro com e-mail já existente (cenário negativo)
- Cadastro sem preenchimento dos campos obrigatórios

### Busca de Produtos

- Busca de produto existente
- Adição de produto ao carrinho
- Validação do carrinho
- Busca de produto inexistente
- Validação da mensagem:
  "No products were found that matched your criteria."


### Fluxo E2E de Compra

✔ Login
✔ Busca de produto
✔ Adição ao carrinho
✔ Validação do carrinho
✔Checkout completo com confirmação do pedido

### Checkout
- Aceitar termos de serviço
- Avançar para a página de checkout

### Checkout Negativo

- Login do usuário
- Busca do produto
- Adição ao carrinho
- Tentativa de checkout sem aceitar os termos de serviço
- Validação da mensagem de bloqueio
- checkout sem produto no carrinho

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

- 13 cenários automatizados
- Selenium WebDriver
- Cucumber BDD
- JUnit 5
- Page Object Model (POM)
- Captura automática de evidências em falhas
- Fluxo E2E completo de compra validado
- Cenários positivos e negativos implementados


## CI/CD com GitHub Actions

O projeto possui integração contínua utilizando GitHub Actions.

A pipeline executa automaticamente:

- Build Maven
- Execução dos testes automatizados
- Geração dos resultados Allure
- Publicação dos relatórios de execução

## A execução ocorre em:

- Push para main
- Pull Request para main
- Execução manual pela aba Actions

## Cross Browser Testing

O framework suporta execução em múltiplos navegadores através de parâmetros Maven.

## Execução Paralela

O projeto suporta execução paralela utilizando JUnit Platform e Cucumber.

### Configuração

Arquivo:

src/test/resources/junit-platform.properties

### Benefícios

- Redução do tempo de execução
- Melhor aproveitamento de recursos
- Preparação para Selenium Grid
- Compatível com CI/CD

### Resultado

- Antes: ~3 minutos
- Depois: ~1 minuto
- Redução aproximada: 64%


## Funcionalidades Implementadas

- Login positivo e negativo
- Logout
- Cadastro positivo e negativo
- Busca de produtos
- Carrinho de compras
- Checkout completo
- Cenários negativos
- Evidências automáticas
- Cross Browser (Chrome e Firefox)
- Execução Paralela com ThreadLocal
- Pipeline CI/CD com GitHub Actions
- Execução via Docker

## Navegadores suportados:

* Google Chrome
* Microsoft Edge
* Mozilla Firefox

```bash
mvn clean test -Dbrowser=chrome

## Relatórios Allure

Gerar os resultados:

```bash


mvn clean test

Tests run: 13
Failures: 0
Errors: 0
Skipped: 0
BUILD SUCCESS

## Arquitetura do projeto

projeto-automacao-selenium
│
├── .github
│   └── workflows
│       └── selenium-ci.yml
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── br/com/ezequias/automacao
│   │   │       ├── factory
│   │   │       │   └── DriverFactory.java
│   │   │       │
│   │   │       ├── pages
│   │   │       │   ├── BasePage.java
│   │   │       │   ├── LoginPage.java
│   │   │       │   ├── RegisterPage.java
│   │   │       │   ├── SearchPage.java
│   │   │       │   ├── CartPage.java
│   │   │       │   ├── CheckoutPage.java
│   │   │       │   └── CompleteCheckoutPage.java
│   │   │       │
│   │   │       └── utils
│   │   │
│   │   └── resources
│   │
│   └── test
│       ├── java
│       │   └── br/com/ezequias/automacao
│       │       ├── hooks
│       │       │   └── Hooks.java
│       │       │
│       │       ├── runner
│       │       │   └── RunCucumberTest.java
│       │       │
│       │       └── stepdefinitions
│       │           ├── LoginSteps.java
│       │           ├── CadastroSteps.java
│       │           ├── CompraSteps.java
│       │           ├── CheckoutSteps.java
│       │           ├── CheckoutNegativoSteps.java
│       │           └── CompleteCheckoutSteps.java
│       │
│       └── resources
│           ├── features
│           │   ├── login.feature
│           │   ├── cadastro.feature
│           │   ├── compra.feature
│           │   ├── checkout.feature
│           │   └── checkout_negativo.feature
│           │
│           └── junit-platform.properties
│
├── evidencias
│
├── Dockerfile
├── .dockerignore
├── pom.xml
├── README.md
└── .gitignore



## Arquitetura do Projeto

O framework foi desenvolvido utilizando os padrões Page Object Model (POM) e BDD com Cucumber.

### Camadas

- **Pages:** encapsulam os elementos e ações das páginas.
- **Step Definitions:** implementam os passos descritos nos cenários BDD.
- **Hooks:** gerenciam abertura e encerramento dos navegadores.
- **Factory:** responsável pela criação e gerenciamento dos drivers.
- **Runner:** configuração de execução dos testes Cucumber/JUnit 5.
- **Features:** cenários escritos em Gherkin.
- **CI/CD:** execução automatizada via GitHub Actions.
- **Docker:** execução dos testes em ambiente isolado e padronizado.