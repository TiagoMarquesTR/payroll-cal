package br.com.tr.payrollcal.model;

import java.util.Date;
import java.util.UUID;

public class CalcOptions {
	private UUID clientId;
	private UUID contractId;
	private final Integer monthDays;
    private final Date competence;
    
    public static class Builder{
    	private UUID clientId;
    	private UUID contractId;
    	private Integer monthDays;
        private Date competence;
        
        public Builder() {};
        
        public Builder clientId(UUID clientId) {
        	this.clientId = clientId;
        	return this;
        }
        
        public Builder contractId(UUID contractId) {
        	this.contractId = contractId;
        	return this;
        }
        
        public Builder monthDays(Integer monthDays) {
        	this.monthDays = monthDays;
        	return this;
        }
        
        public Builder competence(Date competence) {
        	this.competence = competence;
        	return this;
        }
        
        public CalcOptions build() {
        	CalcOptions calcOptions = new CalcOptions(this.monthDays, this.competence);
        	calcOptions.setClientId(this.clientId);
        	calcOptions.setContractId(this.contractId);
        	return calcOptions;
        }
    }
    
    public CalcOptions(Integer monthDays, Date competence) {
    	this.monthDays = monthDays;
		this.competence = competence;
	}
    
    public UUID getClientId() {
		return clientId;
	}

	public UUID getContractId() {
		return contractId;
	}

	public Integer getMonthdays() {
    	return monthDays;
    }
    
    public Date getCompetence() {
    	return competence;
    }

	public void setClientId(UUID clientId) {
		this.clientId = clientId;
	}

	public void setContractId(UUID contractId) {
		this.contractId = contractId;
	}
}
