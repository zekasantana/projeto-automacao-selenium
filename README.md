# 🚀 Projeto de Automação Selenium Web e API Demo Web Shop
![Java](https://img.shields.io/badge/Java-17-orange)
![Selenium](https://img.shields.io/badge/Selenium-4.x-green)
![Cucumber](https://img.shields.io/badge/Cucumber-BDD-brightgreen)
![JUnit5](https://img.shields.io/badge/JUnit-5-blue)
![Maven](https://img.shields.io/badge/Maven-3.9-red)
![Docker](https://img.shields.io/badge/Docker-Enabled-blue)
![GitHub Actions](https://img.shields.io/badge/CI-GitHub_Actions-success)

Framework de automação de testes Web desenvolvido com **Java 17, Selenium WebDriver, Cucumber BDD, JUnit 5, Maven, Docker e GitHub Actions**, seguindo boas práticas de mercado para construção de soluções escaláveis, reutilizáveis e preparadas para integração contínua.

O projeto foi criado com o objetivo de demonstrar a evolução de um framework profissional de automação, cobrindo desde a estrutura inicial até funcionalidades avançadas como execução paralela, Cross Browser Testing, Dockerização e CI/CD.

A arquitetura foi construída utilizando o padrão **Page Object Model (POM)**, promovendo separação de responsabilidades, reutilização de código e facilidade de manutenção. O gerenciamento dos navegadores é realizado através da **DriverFactory**, que suporta diferentes browsers e execução em modo headless para ambientes de integração contínua.

Ao longo das sprints foram implementados cenários funcionais, fluxos End-to-End e cenários negativos, simulando situações reais encontradas em aplicações de e-commerce.

---

# 🎯 Objetivo do Projeto

Demonstrar a construção de um framework de automação moderno capaz de atender às necessidades de equipes ágeis, contemplando:

- Automação Web
- Testes End-to-End
- Testes positivos e negativos
- Testes de API Rest
- Cross Browser Testing
- Execução Paralela
- Evidências Automáticas
- Docker
- Integração Contínua
- Relatórios de Execução

---

# 🛠 Tecnologias Utilizadas

| Tecnologia            | Versão           |
|-----------------------|------------------|
| Java                  | 17               |
| Selenium WebDriver    | 4.x              |
| Cucumber BDD          | 7.x              |
| JUnit Platform        | 1.x              |
| Maven                 | 3.9+             |
| Docker                | Latest           |
| GitHub Actions        | CI/CD            |
| Chrome                | Latest           |
| Firefox               | Latest           |
| Edge                  | Latest           |
| RestAssured           | 5.x              |
| JSON Schema Validator | Contract Testing |
| SonarQube Cloud       | Latest           |
| JaCoCo                | Latest           |
| Allure Report         | Latest           |
---

# 🏗 Arquitetura do Framework

O projeto segue o padrão **Page Object Model (POM)** para garantir organização, reutilização e facilidade de manutenção.

### Factory

Responsável pela criação e gerenciamento dos navegadores.

Classe principal:

- DriverFactory

Funcionalidades:

- Suporte a Chrome
- Suporte a Firefox
- Suporte a Edge
- Execução Headless
- Compatibilidade com Docker
- Compatibilidade com GitHub Actions

---

## 🚀 Funcionalidades

- Automação Web com Selenium WebDriver
- Testes BDD com Cucumber
- Testes unitários com JUnit 5
- Testes de API com RestAssured
- Execução Cross-Browser (Chrome, Firefox e Edge)
- Execução local e remota com Selenium Grid
- Integração Contínua com GitHub Actions
- Análise Estática com SonarQube Cloud
- Cobertura de código com JaCoCo
- Relatórios Allure
- Qualidade de código com Checkstyle, PMD e SpotBugs

### Pages

Responsáveis por encapsular elementos e ações das páginas da aplicação.

Páginas implementadas:

- LoginPage
- RegisterPage
- SearchPage
- CartPage
- CheckoutPage
- CompleteCheckoutPage
- BasePage
- HomePage

---

### Step Definitions

Implementação dos passos BDD escritos em Gherkin.

Classes:

- LoginSteps
- CadastroSteps
- CompraSteps
- CheckoutSteps
- CheckoutNegativoSteps
- CompleteCheckoutSteps

---

### Hooks

Responsáveis por:

- Inicialização do navegador
- Encerramento da execução
- Captura automática de screenshots

---

### Features

Cenários escritos utilizando Cucumber BDD.

Arquivos:

- login.feature
- cadastro.feature
- compra.feature
- checkout.feature
- checkout_negativo.feature

---

# 🔄 Funcionalidades Automatizadas

## Login

### Positivo

- Login com credenciais válidas

### Negativo

- Login com credenciais inválidas

---

## Logout

- Logout realizado com sucesso

---

## Cadastro

### Positivo

- Cadastro de novo usuário

### Negativo

- Cadastro com e-mail já existente
- Cadastro sem preenchimento dos campos obrigatórios

---

## Busca de Produtos

### Positivo

- Busca de produto existente

### Negativo

- Busca de produto inexistente

---

## Carrinho

### Positivo

- Adicionar produto ao carrinho

### Negativo

- Carrinho vazio

---

## Checkout

### Positivo

Fluxo completo de compra:

✔ Login

✔ Busca de Produto

✔ Adicionar ao Carrinho

✔ Aceitar Termos de Serviço

✔ Checkout

✔ Billing Address

✔ Shipping Address

✔ Shipping Method

✔ Payment Method

✔ Confirm Order

✔ Pedido Concluído

### Negativo

- Checkout sem aceitar os termos de serviço

## Testes de API com RestAssured

O projeto também possui testes automatizados de API utilizando RestAssured, JUnit 5 e Hamcrest.

A configuração comum das requisições foi centralizada na classe `ApiBase`, utilizando `RequestSpecification` para reaproveitamento da URL base, Content-Type e configurações de log.

## Testes Unitários

Nesta sprint foram adicionados testes unitários para componentes internos do framework de automação, com o objetivo de aumentar a cobertura de código sem depender da abertura de navegadores reais.

### Cobertura atual

- ✅ TestDataTest
- ✅ DriverFactoryTest

Foram implementados testes para validar a classe responsável pela geração e manipulação de dados utilizados nos cenários automatizados.

### Coberturas principais:

- geração de e-mails dinâmicos;
- validação de dados gerados;
- comportamento dos métodos utilitários;
- cenários positivos e negativos.

### Executar apenas os testes unitários

```bash
mvn test -Dtest=TestDataTest

Resultado:

```text
Tests run: 21
Failures: 0
Errors: 0
Skipped: 0

### Endpoints automatizados

| Método | Endpoint | Validação                                   |
|---|---|---------------------------------------------|
| GET | `/products/1` | Status 200 e campos `id`, `title` e `price` |
| GET | `/products` | Status 200 e lista de produtos não vazia    |
| POST | `/products` | Status 201  |

### Cenários implementados

- Buscar produto por ID
- Buscar todos os produtos
- Criar um novo produto
- Validar status code
- Validar campos do JSON de resposta
- Exibir request e response em caso de falha

---

# ⚡ Execução Paralela

O framework foi preparado para execução paralela utilizando:

- JUnit Platform
- Cucumber
- ThreadLocal WebDriver

Benefícios:

- Redução do tempo de execução
- Melhor aproveitamento de recursos
- Escalabilidade da suíte

## Selenium Grid

O framework suporta execução remota e distribuída utilizando Selenium Grid com Docker.

A infraestrutura é composta por:

- Selenium Hub
- Google Chrome Node
- Mozilla Firefox Node
- Microsoft Edge Node

Os navegadores são executados em containers Docker e as sessões são gerenciadas pelo Selenium Hub.

---

## Relatório Allure Online

O relatório Allure é publicado automaticamente pelo GitHub Actions e pode ser acessado em:

https://zekasantana.github.io/projeto-automacao-selenium/

### Recursos
- Histórico de execuções
- Evidências de testes
- Estatísticas da suíte
- Tendências de execução
- Integração com GitHub Pages

# 🌐 Cross Browser Testing

O framework suporta execução nos navegadores:

- Google Chrome
- Mozilla Firefox
- Microsoft Edge

## Code Quality

- JaCoCo (Code Coverage)
- Checkstyle
- PMD
- SpotBugs
- SonarQube Cloud

## Sprint 2.9 – Testes de API com RestAssured

Nesta sprint, o framework foi evoluído para uma solução híbrida de automação Web e API.

### Implementações

- Adição do RestAssured ao projeto
- Criação da classe `ApiBase`
- Centralização da configuração com `RequestSpecification`
- Implementação de testes GET e POST
- Validação de status code e payload JSON
- Uso de Hamcrest para assertions
- Integração dos testes de API com Maven e JUnit 5
- Manutenção da compatibilidade com a suíte Selenium existente

### Resultado

A suíte passou a executar 16 testes com sucesso, sendo 13 cenários Web e 3 testes de API, sem falhas ou erros.

### Testes de API
- GET /products/1
- GET /products
- POST /products

### Contract Testing
- Validação de contrato utilizando JSON Schema
- Verificação de campos obrigatórios
- Verificação de tipos de dados da resposta
- Detecção de alterações incompatíveis na API

## Estratégia de Execução — Smoke e Regression

O projeto utiliza tags do Cucumber para separar os cenários de teste de acordo com o objetivo da execução.

### Sprint 3.2 — Matriz Cross-Browser no GitHub Actions

Nesta sprint, a pipeline de CI/CD foi evoluída para suportar uma estratégia de execução cross-browser, separando as suítes Web e API.

#### Estratégia de execução

| Suíte | Navegador | Execução |
|---|---|---|
| Smoke | Chrome | 5 cenários |
| Smoke | Firefox | 5 cenários |
| Smoke | Edge | 5 cenários |
| Regression | Chrome | 13 cenários |
| API | Não aplicável | 4 testes |

### Smoke Tests

Os testes `@smoke` representam os principais fluxos críticos da aplicação e permitem uma validação rápida das funcionalidades essenciais.

Atualmente, a suíte Smoke contém 5 cenários:

- Login com sucesso
- Logout com sucesso
- Cadastro de usuário com sucesso
- Adição de produto ao carrinho
- Acesso ao checkout


## ⚙️ Pipeline CI/CD

A pipeline do GitHub Actions está organizada nos seguintes jobs:

- Smoke - Chrome
- Smoke - Firefox
- Smoke - Edge
- Regression - Chrome
- API - RestAssured
- SonarQube Cloud

Todos os jobs são executados automaticamente em Push e Pull Request para a branch `main`.

## 📊 Qualidade de Código

O projeto possui integração com o SonarQube Cloud para análise estática de código.

Também utiliza:

- JaCoCo para cobertura de testes
- Checkstyle para padronização do código
- PMD para análise de boas práticas
- SpotBugs para detecção de possíveis defeitos


#### Implementações

- Matriz de navegadores utilizando strategy.matrix no GitHub Actions.
- Execução dos testes @smoke em Chrome, Firefox e Edge.
- Execução da suíte @regression completa no Chrome.
- Separação dos testes Web e API através do Maven.
- Job independente para testes de API com RestAssured.
- Execução Web em modo headless no ambiente CI.
- fail-fast: false para permitir a conclusão de toda a matriz mesmo em caso de falha em um navegador.
- Artefatos Allure e Surefire separados por suíte e navegador.
- Estabilização do fluxo E2E de checkout com espera explícita para processamento da confirmação do pedido.

Para executar somente os testes Smoke:

```bash
mvn clean test '-Dcucumber.filter.tags=@smoke'

Para executar somente a regressão:

```bash
mvn clean test '-Dcucumber.filter.tags=@regression'


Exemplos:

```bash
mvn clean test -Dbrowser=chrome

mvn clean test -Dbrowser=firefox

mvn clean test -Dbrowser=edge
```bash
mvn clean test -Dbrowser=chrome

## Relatórios Allure

Gerar os resultados:

```bash


mvn clean test


```md
## Resultado atual da suíte

- 13 cenários Web com Selenium e Cucumber
- 4 testes de API com RestAssured
- 17 testes executados com sucesso
- 0 falhas
- 0 erros
- 0 testes ignorados
- BUILD SUCCESS


## Arquitetura do Projeto

O projeto utiliza uma arquitetura híbrida para automação de testes Web e API, organizada para facilitar manutenção, reutilização de código, escalabilidade e execução em diferentes ambientes.

### Arquitetura

```text
                    Testes Automatizados
                            |
                     DriverFactory
                            |
              +-------------+-------------+
              |                           |
        Execução Local              Execução Remota
              |                           |
        WebDriver Local              RemoteWebDriver
                                          |
                                   Selenium Grid Hub
                                   localhost:4444
                                          |
                         +----------------+----------------+
                         |                |                |
                      Chrome          Firefox            Edge
                       Node             Node              Node

```text
projeto-automacao-selenium
│
├── .github
│   └── workflows
│       ├── selenium-ci.yml
│       └── allure-report.yml
│
├── evidencias
│   └── screenshots de falhas
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── br
│   │   │       └── com
│   │   │           └── ezequias
│   │   │               └── automacao
│   │   │                   ├── core
│   │   │                   │   └── configurações gerais do framework
│   │   │                   │
│   │   │                   ├── factory
│   │   │                   │   └── DriverFactory.java
│   │   │                   │
│   │   │                   ├── pages
│   │   │                   │   ├── BasePage.java
│   │   │                   │   ├── LoginPage.java
│   │   │                   │   ├── RegisterPage.java
│   │   │                   │   ├── SearchPage.java
│   │   │                   │   ├── CartPage.java
│   │   │                   │   ├── CheckoutPage.java
│   │   │                   │   └── CompleteCheckoutPage.java
│   │   │                   │
│   │   │                   └── utils
│   │   │                       └── classes utilitárias
│   │   │
│   │   └── resources
│   │       └── arquivos de configuração da aplicação
│   │
│   └── test
│       ├── java
│       │   └── br
│       │       └── com
│       │           └── ezequias
│       │               └── automacao
│       │                   ├── api
│       │                   │   ├── ApiBase.java
│       │                   │   └── ProductApiTest.java
│       │                   │
│       │                   ├── hooks
│       │                   │   └── Hooks.java
│       │                   │
│       │                   ├── runner
│       │                   │   └── RunCucumberTest.java
│       │                   │
│       │                   └── stepdefinitions
│       │                       ├── LoginSteps.java
│       │                       ├── CadastroSteps.java
│       │                       ├── CompraSteps.java
│       │                       ├── CheckoutSteps.java
│       │                       ├── CheckoutNegativoSteps.java
│       │                       └── CompleteCheckoutSteps.java
│       │
│       └── resources
│           ├── features
│           │   ├── login.feature
│           │   ├── cadastro.feature
│           │   ├── compra.feature
│           │   ├── checkout.feature
│           │   └── checkout_negativo.feature
│           │
│           ├── schemas
│           │   └── product-schema.json
│           │
│           └── junit-platform.properties
│
├── target
│   ├── allure-results
│   ├── surefire-reports
│   ├── site
│   │   └── jacoco
│   └── resultados gerados pelo Maven
│
├── .dockerignore
├── .gitignore
├── Dockerfile
├── pom.xml
└── README.md

┌──────────────────────────────────────────────────────────────┐
│                    CAMADA DE AUTOMAÇÃO                       │
├───────────────────────────────┬──────────────────────────────┤
│          TESTES WEB           │          TESTES API          │
│                               │                              │
│ Selenium WebDriver            │ RestAssured                  │
│ Cucumber BDD                  │ JUnit 5                      │
│ Page Object Model             │ Hamcrest                     │
│ JUnit Platform                │ JSON Schema Validator        │
│                               │                              │
└───────────────────────────────┴──────────────────────────────┘
                              │
                              ▼
┌──────────────────────────────────────────────────────────────┐
│                    CAMADA DE ESTRUTURA                       │
├───────────────────────────────┬──────────────────────────────┤
│ DriverFactory                 │ ApiBase                      │
│ BasePage                      │ RequestSpecification         │
│ Hooks                         │ Schemas JSON                 │
│ Step Definitions              │ Testes de contrato           │
└───────────────────────────────┴──────────────────────────────┘
                              │
                              ▼
┌──────────────────────────────────────────────────────────────┐
│                    CAMADA DE QUALIDADE                       │
├──────────────────────────────────────────────────────────────┤
│ Checkstyle                                                   │
│ PMD                                                          │
│ SpotBugs                                                     │
│ JaCoCo                                                       │
└──────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌──────────────────────────────────────────────────────────────┐
│                    EXECUÇÃO E ENTREGA                        │
├───────────────────────────────┬──────────────────────────────┤
│ Execução Local                │ Integração Contínua          │
│ Maven                         │ GitHub Actions               │
│ Chrome / Firefox              │ Execução Headless            │
│ Docker                        │ Publicação Allure            │
└───────────────────────────────┴──────────────────────────────┘
                              │
                              ▼
┌──────────────────────────────────────────────────────────────┐
│                      RELATÓRIOS                              │
├──────────────────────────────────────────────────────────────┤
│ Allure Report                                                │
│ GitHub Pages                                                 │
│ JaCoCo Report                                                │
│ Surefire Reports                                             │
│ Screenshots de falhas                                        │
└──────────────────────────────────────────────────────────────┘


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