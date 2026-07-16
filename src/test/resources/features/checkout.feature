Feature: Checkout de Produto

  Scenario: Avançar para o checkout com sucesso
    Given que o usuário possui um produto no carrinho
    When aceita os termos de serviço
    And clica no botão checkout
    Then deve visualizar a página de checkout