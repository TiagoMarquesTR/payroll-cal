package br.com.tr.payrollcal.model;

import java.util.UUID;

public class Rubric {
    private final UUID rubricId = UUID.randomUUID();
    private final String name;

	public Rubric(String name){
        this.name = name;
    }

    public UUID getRubricId() {
        return rubricId;
    }

    public String getName() {
        return name;
    }
}
