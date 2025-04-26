package com.example.wallet_operation_service;

import com.example.wallet_operation_service.model.request.TransactionRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Add more validation tests.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ValidationTests {

    @LocalServerPort
    private int randomServerPort;

    @Test
    @DisplayName("Credit - Customer Not exist.")
    void creditNotExistingCustomerValidation() throws URISyntaxException {
        TransactionRequest transactionRequest = new TransactionRequest(1L, 999L, 217.0);
        RestTemplate restTemplate = new RestTemplate();
        URI uri = new URI("http://localhost:" + randomServerPort + "/api/credit");
        Assertions.assertThrows(HttpClientErrorException.class, () -> restTemplate.postForEntity(uri, transactionRequest, String.class));
    }

    @Test
    @DisplayName("Credit - Non Unique transaction id")
    void creditNotUniqueTransactionIdValidation() throws URISyntaxException {
        TransactionRequest transactionRequest = new TransactionRequest(1L, 1L, 7268.0);
        RestTemplate restTemplate = new RestTemplate();
        URI uri = new URI("http://localhost:" + randomServerPort + "/api/credit");
        Assertions.assertThrows(HttpClientErrorException.class, () -> restTemplate.postForEntity(uri, transactionRequest, String.class));
    }

    @Test
    @DisplayName("Withdrawal - Customer Not exist.")
    void withdrawalNotExistingCustomerValidation() throws URISyntaxException {
        TransactionRequest transactionRequest = new TransactionRequest(923782836L, 9997L, 196.0);
        RestTemplate restTemplate = new RestTemplate();
        URI uri = new URI("http://localhost:" + randomServerPort + "/api/withdrawal");
        Assertions.assertThrows(HttpClientErrorException.class, () -> restTemplate.postForEntity(uri, transactionRequest, String.class));
    }

    @Test
    @DisplayName("Withdrawal - Non Unique transaction id")
    void withdrawalNotUniqueTransactionIdValidation() throws URISyntaxException {
        TransactionRequest transactionRequest = new TransactionRequest(4L, 2L, 50.0);
        RestTemplate restTemplate = new RestTemplate();
        URI uri = new URI("http://localhost:" + randomServerPort + "/api/withdrawal");
        Assertions.assertThrows(HttpClientErrorException.class, () -> restTemplate.postForEntity(uri, transactionRequest, String.class));
    }

    @Test
    @DisplayName("Withdrawal - Insufficient balance.")
    void withdrawalInsufficientAmountRequest() throws URISyntaxException {
        TransactionRequest transactionRequest = new TransactionRequest(3L, 3L, 9999999999.0);
        RestTemplate restTemplate = new RestTemplate();
        URI uri = new URI("http://localhost:" + randomServerPort + "/api/withdrawal");
        Assertions.assertThrows(HttpClientErrorException.class, () -> restTemplate.postForEntity(uri, transactionRequest, String.class));
    }

    @Test
    @DisplayName("Wallet Details - Customer not exist.")
    void getCustomerWalletDetailsNotExistingCustomer() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = new URI("http://localhost:" + randomServerPort + "/api/walletDetails/21314121211");
        Assertions.assertThrows(HttpClientErrorException.class, () -> restTemplate.getForEntity(uri, String.class));
    }
}
