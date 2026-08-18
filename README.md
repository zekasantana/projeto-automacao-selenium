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
| Mockito               | Latest           |
| JUnit 5               | Latest           |
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

## 🧪 Testes Unitários

Além dos testes automatizados Web e API, o projeto possui uma camada de testes unitários para validar componentes internos do framework de automação.

### BasePageTest

A classe `BasePageTest` foi criada para validar os principais comportamentos da `BasePage`, responsável por centralizar operações reutilizadas pelas Page Objects.

Os testes utilizam **JUnit 5** e **Mockito**, permitindo validar o comportamento da classe de forma isolada, sem necessidade de iniciar um navegador real.

Atualmente, a suíte da `BasePage` possui:

- 14 testes unitários
- 0 falhas
- 0 erros
- 0 testes ignorados

Entre os comportamentos validados estão:

- localização de elementos com `WebDriver`;
- clique em elementos;
- preenchimento de campos;
- limpeza de campos antes da escrita;
- obtenção de textos;
- validação de visibilidade;
- interação com elementos habilitados;
- tratamento de esperas explícitas;
- seleção de opções em elementos HTML `<select>`;
- integração da `BasePage` com componentes do Selenium WebDriver.

### Tecnologias utilizadas nos testes unitários

- JUnit 5
- Mockito
- Selenium WebDriver
- Maven Surefire Plugin
- JaCoCo

### Testes Unitários

- Implementação de testes unitários para `DriverFactory`
- Implementação de testes unitários para `BasePage`
- Uso de Mockito para isolamento das dependências Selenium
- Validação integrada ao Maven

### LoginPageTest

A classe LoginPageTest foi criada para validar os principais comportamentos da LoginPage de forma isolada, sem necessidade de iniciar um navegador real.

Os testes utilizam *JUnit 5* e *Mockito*, incluindo MockedStatic<DriverFactory> para interceptar a chamada DriverFactory.getDriver() e fornecer um WebDriver mockado durante a execução dos testes.

Atualmente, a suíte da LoginPage possui:

- 12 testes unitários
- 0 falhas
- 0 erros
- 0 testes ignorados

Entre os comportamentos validados estão:

- acesso à página de login;
- obtenção da mensagem de erro;
- preenchimento do e-mail;
- preenchimento da senha;
- clique no botão Entrar;
- execução do fluxo de login;
- validação de login realizado com sucesso;
- validações relacionadas aos elementos da página;
- comportamento de logout;
- integração da LoginPage com a DriverFactory.

### Sprint 3.6 - Testes Unitários da RegisterPage

- Criada a classe RegisterPageTest.java
- Implementados 8 testes unitários com JUnit 5 e Mockito
- Cobertura dos fluxos:
    - acesso à tela de cadastro
    - preenchimento dos dados
    - clique no botão de registro
    - cadastro realizado com sucesso
    - cadastro não realizado
    - mensagem de e-mail já existente
    - validação de campos obrigatórios

### Sprint 3.8 - Testes Unitários da SearchPage

- Criada a classe SearchPageTest.java
- Implementados 7 testes unitários com JUnit 5 e Mockito
- Cobertura dos métodos públicos da SearchPage
- Validação do fluxo de busca de produto
- Validação dos cenários de produto encontrado e não encontrado
- Validação do clique no produto
- Validação da mensagem de produto não encontrado
- Uso de MockedStatic<DriverFactory> para isolamento do WebDriver
- Uso de Spy e ArgumentCaptor para validação das interações da Page Object

## Sprint 3.9 — Testes Unitários da CartPage

Nesta Sprint foram implementados testes unitários para a classe CartPage, utilizando JUnit 5 e Mockito.

Testes implementados

Foram adicionados 10 testes unitários cobrindo os principais comportamentos da CartPage:

1. deveObterMensagemCarrinho
2. deveRetornarTrueQuandoCarrinhoEstaVazio
3. deveRetornarFalseQuandoCarrinhoNaoEstaVazio
4. deveRetornarTrueQuandoProdutoEstaNoCarrinho
5. deveRetornarTrueQuandoProdutoEstaNoCarrinhoAposRefresh
6. deveRetornarFalseQuandoProdutoNaoEstaNoCarrinho
7. deveAcessarCarrinho
8. deveAdicionarProdutoAoCarrinhoComSucesso
9. deveTentarAdicionarProdutoNovamenteQuandoOcorrerErro
10. deveLancarExcecaoQuandoFalharAoAdicionarProdutoDuasVezes

## Sprint 4.0 – Testes Unitários da CheckoutPage

Nesta sprint foram implementados testes unitários para a classe CheckoutPage, utilizando JUnit 5 e Mockito, com o objetivo de aumentar a cobertura de testes da camada Page Object sem necessidade de inicializar um navegador real.

Testes implementados

Foram adicionados 7 testes unitários:

* deveClicarNoCheckboxDeTermosAoAceitarTermos
* deveClicarNoBotaoCheckoutAoSolicitarCheckout
* deveRetornarTrueQuandoUrlETituloIndicamPaginaDeCheckout
* deveRetornarFalseQuandoPaginaDeCheckoutNaoForIdentificada
* deveRetornarTrueQuandoMensagemDeTermosEstiverVisivel
* deveRetornarFalseQuandoMensagemDeTermosNaoEstiverVisivel
* deveRetornarTextoDaMensagemDeTermos

Sprint 4.1 – Testes Unitários da CompleteCheckoutPage

Nesta sprint foram implementados testes unitários para a classe CompleteCheckoutPage, utilizando JUnit 5 e Mockito para validar os principais fluxos do checkout completo sem necessidade de abrir um navegador real.

Testes implementados

Foram adicionados 17 testes unitários cobrindo:

* preenchimento dos dados de cobrança;
* preenchimento dinâmico do e-mail;
* fluxo quando o formulário de cobrança não está disponível;
* validação da etapa Shipping Address;
* continuação do endereço de entrega;
* validação da etapa Shipping Method;
* seleção do método de entrega;
* validação da etapa Payment Method;
* seleção do método de pagamento;
* validação da etapa Payment Information;
* continuação das informações de pagamento;
* confirmação do pedido;
* validação do pedido realizado com sucesso;
* cenários negativos com IllegalStateException.

Estratégia utilizada

Os testes utilizam:

* JUnit 5;
* Mockito;
* MockedStatic<DriverFactory>;
* WebDriver mockado;
* WebElement mockado;
* spy da CompleteCheckoutPage;
* mocks dos métodos herdados da BasePage;
* simulação dos comportamentos usados por WebDriverWait e ExpectedConditions.

Estratégia utilizada

Os testes utilizam:

* JUnit 5
* Mockito
* MockedStatic<DriverFactory>
* WebDriver mockado
* spy da CheckoutPage
* mocks dos métodos herdados da BasePage

Abordagem utilizada

* JUnit 5
* Mockito
* MockedStatic<DriverFactory>
* spy da CartPage
* mocks de WebDriver, WebElement e WebDriver.Navigation
* mocks de ExpectedCondition
* mock estático de ExpectedConditions
* validação de retry
* validação de exceções
* execução sem navegador real

## Sprint 4.2 — Testes Unitários da HomePage

Nesta sprint foram implementados os testes unitários da classe HomePage, responsável pelo acesso à página inicial da aplicação Demo Web Shop.

Testes implementados

Foi criado o arquivo:

src/test/java/br/com/ezequias/automacao/pages/HomePageTest.java

A classe possui 1 teste unitário:

1. deveAcessarHome

O teste valida que o método acessarHome() executa corretamente:

driver.get("https://demowebshop.tricentis.com/");

Estratégia utilizada

Os testes foram desenvolvidos utilizando:

* JUnit 5
* Mockito
* MockedStatic<DriverFactory>
* WebDriver mockado
* execução sem abertura de navegador real

O DriverFactory.getDriver() é interceptado durante o teste para retornar uma instância mockada de WebDriver, mantendo o teste isolado da infraestrutura do Selenium.

## Sprint 4.4 — API PATCH, DELETE e Cenários Negativos

Nesta sprint foram ampliados os testes de API REST utilizando RestAssured, adicionando operações de atualização, exclusão e cenários negativos para produtos da Fake Store API.

Implementações

* Atualização parcial de produto com PATCH /products/{id}
* Exclusão de produto com DELETE /products/{id}
* Validação de produto inexistente com GET /products/{id}
* Validação de atualização de produto inexistente com PATCH
* Validação de exclusão de produto inexistente com DELETE

Cenários adicionados

* deveAtualizarProdutoParcialmente
* deveDeletarProduto
* deveRetornarBodyVazioQuandoProdutoNaoExiste
* deveSimularAtualizacaoDeProdutoInexistente
* deveRetornarBodyVazioAoExcluirProdutoInexistente

Comportamento observado da Fake Store API

A Fake Store API possui alguns comportamentos específicos para recursos inexistentes:

* GET /products/999999 retorna HTTP 200 com body vazio
* PATCH /products/999999 retorna HTTP 200 simulando a atualização do recurso
* DELETE /products/999999 retorna HTTP 200 com body vazio

Os testes foram implementados considerando o comportamento real retornado pela API, sem assumir códigos HTTP diferentes dos efetivamente recebidos.

## Sprint 4.4 — Validações avançadas de API

Nesta sprint, a cobertura dos testes de API foi ampliada com RestAssured, incluindo validações de headers, tempo de resposta, cenários negativos e autenticação com Bearer Token.

### Validações de produtos

Foram adicionadas as seguintes validações à `ProductApiTest`:

- Validação do header `Content-Type`;
- Validação de resposta no formato JSON;
- Validação do tempo de resposta inferior a 5 segundos;
- Busca de produto inexistente;
- Atualização simulada de produto inexistente;
- Exclusão de produto inexistente.

### Testes de autenticação

Foi criada a classe `AuthenticationApiTest`, utilizando a API DummyJSON, com os seguintes cenários:

- Login com credenciais válidas;
- Validação dos tokens de acesso e atualização;
- Acesso ao endpoint protegido com Bearer Token;
- Rejeição de credenciais inválidas;
- Rejeição de acesso ao endpoint protegido sem token;
- Validação dos status HTTP `200`, `400` e `401`.

### Resultado

A validação completa foi executada com:

```bash
mvn clean verify

### Resultado atual dos testes

Após a inclusão dos testes unitários da LoginPage, a validação completa do projeto apresentou:

### Executando somente os testes da BasePage

```bash

### Executar apenas os testes unitários

```bash
mvn test -Dtest=TestDataTest
mvn test -Dtest=BasePageTest
mvn test -Dtest=LoginPageTest
mvn test -Dtest=RegisterPageTest
mvn test -Dtest=SearchPageTest
mvn test -Dtest=CheckoutPageTest
mvn test -Dtest=CompleteCheckoutPageTest
mvn test -Dtest=HomePageTest


## Uso de MockedStatic<DriverFactory> para isolamento do WebDriver
  - Validação individual:
  - 17 testes
  - 0 falhas
  - 0 erros

Resultado:

Validação completa

Comando:

mvn clean verify

``text
Tests run: 121
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



src
├── main
│   └── java
│       └── br.com.ezequias.automacao
│           ├── factory
│           ├── pages
│           │   └── BasePage.java
│           └── ...
│
└── test
    └── java
        └── br.com.ezequias.automacao
            ├── pages
            │   └── BasePageTest.java
            ├── runner
            └── stepdefinitions

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