Feature: Checkout de Produto

  Scenario: Avançar para o checkout com sucesso
    Given que o usuário possui um produto no carrinho
    When aceita os termos de serviço
    And clica no botão checkout
    Then deve visualizar a página de checkout

  Scenario: Finalizar compra com sucesso
    Given que o usuário está na página de checkout com um produto
    When preenche os dados obrigatórios de cobrança
    And seleciona o endereço de entrega
    And seleciona o método de entrega
    And seleciona o método de pagamento
    And confirma as informações de pagamento
    And confirma o pedido
    Then deve visualizar a mensagem de pedido realizado com sucesso