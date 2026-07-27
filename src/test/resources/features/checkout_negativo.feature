Feature: Validação negativa do checkout

  @regression
  Scenario: Tentar realizar checkout sem aceitar os termos de serviço

    Given que possuo um produto no carrinho para o checkout negativo
    When tento realizar o checkout sem aceitar os termos de serviço
    Then devo visualizar a mensagem de obrigatoriedade dos termos

  @regression
  Scenario: Tentar realizar checkout sem possuir produtos no carrinho

    Given que o usuário está logado
    When acessa o carrinho sem produtos
    Then o carrinho deve estar vazio