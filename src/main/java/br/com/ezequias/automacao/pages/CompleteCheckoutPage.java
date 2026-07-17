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
            By.cssSelector("#billing-buttons-container input");

    // Shipping Address
    private final By btnContinuarShippingAddress =
            By.cssSelector("#shipping-buttons-container input");

    // Shipping Method
    private final By shippingMethod =
            By.id("shippingoption_0");

    private final By btnContinuarShippingMethod =
            By.cssSelector("#shipping-method-buttons-container input");

    // Payment Method
    private final By paymentMethod =
            By.id("paymentmethod_0");

    private final By btnContinuarPaymentMethod =
            By.cssSelector("#payment-method-buttons-container input");

    // Payment Information
    private final By btnContinuarPaymentInformation =
            By.cssSelector("#payment-info-buttons-container input");

    // Confirm Order
    private final By btnConfirmarPedido =
            By.cssSelector("#confirm-order-buttons-container input");

    // Order Completed
    private final By mensagemPedidoConcluido =
            By.cssSelector(".section.order-completed .title strong");


    public void preencherDadosCobranca() {
        escrever(billingFirstName, "Ezequias");
        escrever(billingLastName, "Teste");
        escrever(billingEmail, gerarEmailDinamico());
        selecionarPorTexto(billingCountry, "Brazil");
        escrever(billingCity, "Barueri");
        escrever(billingAddress, "Rua de Teste, 100");
        escrever(billingZipCode, "06400-000");
        escrever(billingPhone, "11999999999");
        clicar(btnContinuarBilling);

    }

    public void continuarEnderecoEntrega() {
        clicar(btnContinuarShippingAddress);
    }

    public void selecionarMetodoEntrega() {
        clicar(shippingMethod);
        clicar(btnContinuarShippingMethod);
    }

    public void selecionarMetodoPagamento() {
        clicar(paymentMethod);
        clicar(btnContinuarPaymentMethod);
    }

    public void continuarInformacoesPagamento() {
        clicar(btnContinuarPaymentInformation);
    }

    public void confirmarPedido() {
        clicar(btnConfirmarPedido);
    }

    public boolean pedidoRealizadoComSucesso() {
        return elementoEstaVisivel(mensagemPedidoConcluido)
                && obterTexto(mensagemPedidoConcluido)
                .contains("Your order has been successfully processed!");
    }

    private String gerarEmailDinamico() {
        return "checkout" + System.currentTimeMillis() + "@teste.com";

    }

}