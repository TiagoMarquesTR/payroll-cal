package br.com.tr.payrollcal.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Client {
	@JsonAlias(value = "client_id")
    private UUID clientId;
    private String name;

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
