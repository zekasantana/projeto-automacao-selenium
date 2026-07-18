Feature: Compra de Produto

  Scenario: Realizar compra com sucesso
    Given que o usuário realizou login para efetuar uma compra
    When busca pelo produto "14.1-inch Laptop"
    And adiciona o produto ao carrinho
    And acessa o carrinho
    Then o produto deve estar no carrinho

  Scenario: Buscar produto inexistente

    Given que o usuário realizou login para efetuar uma compra
    When busca pelo produto inexistente "ProdutoInexistente123"
    Then deve visualizar a mensagem de produto não encontrado