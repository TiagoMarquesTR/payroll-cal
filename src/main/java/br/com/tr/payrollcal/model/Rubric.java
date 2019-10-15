package br.com.tr.payrollcal.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Rubric {
	@JsonAlias(value = "rubric_id")
    private UUID rubricId;
    private String name;

    public Rubric() {};
    
	public Rubric(UUID rubricId, String name) {
    	this.rubricId = rubricId;
    	this.name = name;
    }
    
    public void setRubricId(UUID rubricId) {
		this.rubricId = rubricId;
	}

	public void setName(String name) {
		this.name = name;
	}

    public UUID getRubricId() {
        return rubricId;
    }

    public String getName() {
        return name;
    }
    
    public static final String HORAS_TRABALHADAS = "Horas_trabalhadas";
    public static final String INSS = "INSS";
}
