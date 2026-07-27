Feature: Cadastro de usuário

  @smoke @regression
  Scenario: Realizar cadastro com sucesso

    Given que o usuário acessa a tela de cadastro
    When preencher os dados válidos
    And clicar em registrar
    Then o cadastro deve ser realizado com sucesso


  @regression
  Scenario: Tentar cadastrar usuário com e-mail já existente

    Given que estou na página de cadastro
    When informo os dados de um usuário já cadastrado
    And clico no botão Register
    Then devo visualizar a mensagem de erro de e-mail já existente


  @regression
  Scenario: Tentar cadastrar usuário sem preencher campos obrigatórios

    Given que estou na página de cadastro
    When não preencho os campos obrigatórios
    And clico no botão Register
    Then devo visualizar mensagens de campos obrigatórios