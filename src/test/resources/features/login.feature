Feature: Login

  Scenario: Realizar login com sucesso

    Given que o usuário está na página inicial
    When acessa a tela de login
    And informa um usuário e senha válidos
    Then deve visualizar a página inicial autenticada


  Scenario: Realizar login com credenciais inválidas

    Given que o usuário está na página inicial
    When acessa a tela de login
    And informa um usuário e senha inválidos
    Then devo visualizar uma mensagem de erro