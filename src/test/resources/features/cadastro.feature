Feature: Cadastro de usuário

  Scenario: Realizar cadastro com sucesso

    Given que o usuário acessa a tela de cadastro
    When preencher os dados válidos
    And clicar em registrar
    Then o cadastro deve ser realizado com sucesso