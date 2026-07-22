package br.com.ezequias.automacao.pages;

import org.openqa.selenium.By;

public class CompleteCheckoutPage extends BasePage {

    // Billing Address
    private final By billingFirstName =
            By.id("BillingNewAddress_FirstName");

    private final By billingLastName =
            By.id("BillingNewAddress_LastName");

    private final By billingEmail =
            By.id("BillingNewAddress_Email");

    private final By billingCountry =
            By.id("BillingNewAddress_CountryId");

    private final By billingCity =
            By.id("BillingNewAddress_City");

    private final By billingAddress =
            By.id("BillingNewAddress_Address1");

    private final By billingZipCode =
            By.id("BillingNewAddress_ZipPostalCode");

    private final By billingPhone =
            By.id("BillingNewAddress_PhoneNumber");

    private final By btnContinuarBilling =
            By.cssSelector(
                    "#billing-buttons-container input[value='Continue']"
            );

    // Shipping Address
    private final By shippingAddressSection =
            By.id("checkout-step-shipping");

    private final By btnContinuarShippingAddress =
            By.cssSelector(
                    "#shipping-buttons-container input[value='Continue']"
            );

    // Shipping Method
    private final By shippingMethodSection =
            By.id("checkout-step-shipping-method");

    private final By shippingMethod =
            By.id("shippingoption_0");

    private final By btnContinuarShippingMethod =
            By.cssSelector(
                    "#shipping-method-buttons-container input[value='Continue']"
            );

    // Payment Method
    private final By paymentMethodSection =
            By.id("checkout-step-payment-method");

    private final By paymentMethod =
            By.id("paymentmethod_0");

    private final By btnContinuarPaymentMethod =
            By.cssSelector(
                    "#payment-method-buttons-container input[value='Continue']"
            );

    // Payment Information
    private final By paymentInformationSection =
            By.id("checkout-step-payment-info");

    private final By btnContinuarPaymentInformation =
            By.cssSelector(
                    "#payment-info-buttons-container input[value='Continue']"
            );

    // Confirm Order
    private final By confirmOrderSection =
            By.id("checkout-step-confirm-order");

    private final By btnConfirmarPedido =
            By.cssSelector(
                    "#confirm-order-buttons-container input[value='Confirm']"
            );

    // Order Completed
    private final By mensagemPedidoConcluido =
            By.cssSelector(
                    ".section.order-completed .title strong"
            );

    public void preencherDadosCobranca() {

        /*
         * O formulário só é preenchido quando o usuário
         * ainda não possui endereço de cobrança cadastrado.
         */
        if (elementoEstaVisivel(billingFirstName)) {

            escrever(billingFirstName, "Ezequias");
            escrever(billingLastName, "Santana");

            if (elementoEstaVisivel(billingEmail)) {
                escrever(
                        billingEmail,
                        "checkout"
                                + System.currentTimeMillis()
                                + "@teste.com"
                );
            }

            selecionarPorTexto(billingCountry, "Brazil");
            escrever(billingCity, "Barueri");
            escrever(billingAddress, "Rua Teste, 100");
            escrever(billingZipCode, "06400-000");
            escrever(billingPhone, "11999999999");
        }

        clicar(btnContinuarBilling);

        if (!elementoEstaVisivel(shippingAddressSection)) {
            throw new IllegalStateException(
                    "A etapa Shipping Address não foi carregada após o Billing Address."
            );
        }
    }

    public void continuarEnderecoEntrega() {

        if (!elementoEstaVisivel(btnContinuarShippingAddress)) {
            throw new IllegalStateException(
                    "O botão Continue da etapa Shipping Address não está disponível."
            );
        }

        clicar(btnContinuarShippingAddress);

        if (!elementoEstaVisivel(shippingMethodSection)) {
            throw new IllegalStateException(
                    "A etapa Shipping Method não foi carregada."
            );
        }
    }

    public void selecionarMetodoEntrega() {

        if (!elementoEstaVisivel(shippingMethod)) {
            throw new IllegalStateException(
                    "O método de entrega não está disponível. Verifique se o carrinho possui produto."
            );
        }

        clicar(shippingMethod);
        clicar(btnContinuarShippingMethod);

        if (!elementoEstaVisivel(paymentMethodSection)) {
            throw new IllegalStateException(
                    "A etapa Payment Method não foi carregada."
            );
        }
    }

    public void selecionarMetodoPagamento() {

        if (!elementoEstaVisivel(paymentMethod)) {
            throw new IllegalStateException(
                    "O método de pagamento não está disponível."
            );
        }

        clicar(paymentMethod);
        clicar(btnContinuarPaymentMethod);

        if (!elementoEstaVisivel(paymentInformationSection)) {
            throw new IllegalStateException(
                    "A etapa Payment Information não foi carregada."
            );
        }
    }

    public void continuarInformacoesPagamento() {

        clicar(btnContinuarPaymentInformation);

        if (!elementoEstaVisivel(confirmOrderSection)) {
            throw new IllegalStateException(
                    "A etapa Confirm Order não foi carregada."
            );
        }
    }

    public void confirmarPedido() {
        clicar(btnConfirmarPedido);
    }

    public boolean pedidoRealizadoComSucesso() {
        return aguardarTextoVisivel(
                mensagemPedidoConcluido,
                "Your order has been successfully processed!"
        );
    }
}