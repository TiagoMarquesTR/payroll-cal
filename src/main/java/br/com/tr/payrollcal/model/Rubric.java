package br.com.tr.payrollcal.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Rubric {

	public static final String HORAS_TRABALHADAS = "Horas Trabalhadas";
	public static final String INSS = "INSS";
	public static final String FGTS = "FGTS";
	public static final String IRRF = "IRRF";
	public static final String HORAS_EXTRAS = "Horas-extras";
    
    @JsonAlias(value = "client_id") 		private UUID clientId;
	@JsonAlias(value = "rubric_id") 		private UUID rubricId;
	@JsonAlias(value = "name") 				private String name;
    @JsonAlias(value = "type") 				private char type;
    @JsonAlias(value = "unit") 				private char unit;
    @JsonAlias(value = "consistes_net_pay") private char consistsNetPay;
    @JsonAlias(value = "inss_base") 		private char inssBase;
    @JsonAlias(value = "fgts_base") 		private char fgtsBase;
    @JsonAlias(value = "irrf_base") 		private char irrfBase;
    @JsonAlias(value = "overtime_base") 	private char overtimeBase;
    @JsonAlias(value = "calculation_base") 	private int calculationBase;
    @JsonAlias(value = "rate") 				private double rate;
    
	public Rubric() {};
    
	public Rubric(UUID rubricId, String name) {
    	this.rubricId = rubricId;
    	this.name = name;
    }
    
    public UUID getClientId() {
		return clientId;
	}

	public void setClientId(UUID clientId) {
		this.clientId = clientId;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public char getUnit() {
		return unit;
	}

	public void setUnit(char unit) {
		this.unit = unit;
	}

	public char getConsistsNetPay() {
		return consistsNetPay;
	}

	public void setConsistsNetPay(char consistsNetPay) {
		this.consistsNetPay = consistsNetPay;
	}

	public char getInssBase() {
		return inssBase;
	}

	public void setInssBase(char inssBase) {
		this.inssBase = inssBase;
	}

	public char getFgtsBase() {
		return fgtsBase;
	}

	public void setFgtsBase(char fgtsBase) {
		this.fgtsBase = fgtsBase;
	}

	public char getIrrfBase() {
		return irrfBase;
	}

	public void setIrrfBase(char irrfBase) {
		this.irrfBase = irrfBase;
	}

	public char getOvertimeBase() {
		return overtimeBase;
	}

	public void setOvertimeBase(char overtimeBase) {
		this.overtimeBase = overtimeBase;
	}

	public int getCalculationBase() {
		return calculationBase;
	}

	public void setCalculationBase(int calculationBase) {
		this.calculationBase = calculationBase;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
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
}
