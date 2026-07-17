Feature: Cadastro de usuário

  Scenario: Realizar cadastro com sucesso

    Given que o usuário acessa a tela de cadastro
    When preencher os dados válidos
    And clicar em registrar
    Then o cadastro deve ser realizado com sucesso


  Scenario: Tentar cadastrar usuário com e-mail já existente

    Given que estou na página de cadastro
    When informo os dados de um usuário já cadastrado
    And clico no botão Register
    Then devo visualizar a mensagem de erro de e-mail já existente