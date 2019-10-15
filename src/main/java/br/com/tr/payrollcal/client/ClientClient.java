package br.com.tr.payrollcal.client;

import org.springframework.web.client.RestTemplate;

import br.com.tr.payrollcal.model.Client;

public class ClientClient {
    RestTemplate restTemplate = new RestTemplate();

    public Client findById(String clientId) {
    	return restTemplate.getForObject("http://localhost:8080/client/" + clientId, Client.class);
    }
}