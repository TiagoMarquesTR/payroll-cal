package br.com.tr.payrollcal.model;

import java.util.UUID;

public class Client {
    private final UUID clientId = UUID.randomUUID();;

    public UUID getClientId() {
        return clientId;
    }
}
