package br.com.tr.payrollcal.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Payroll {
    private final UUID clientId;
    private final UUID contractId;
    private final Date competence;
    private Double inssBaseAmount = 0.00;
    private Double inssBaseExcessAmount = 0.00;
    private Double fgtsBaseAmount = 0.00;
    private Double irrfBaseAmount = 0.00;
    private Double overtimeBaseAmount = 0.00;
    private Double grossPay = 0.00;
    private Double deductions = 0.00;
    private List<PayrollDetail> payrollDetails;

    public void addDetail(UUID rubricId, Double calculatedValue) {
    	payrollDetails.add(new PayrollDetail.Builder(this.clientId, this.contractId,
    												 rubricId, this.competence)
    												.calculatedValue(calculatedValue).build());
    }
    
    public void addDetail(UUID rubricId, Double referenceValue, Double calculatedValue) {
    	payrollDetails.add(new PayrollDetail.Builder(this.clientId, this.contractId,
    												 rubricId, this.competence)
    												.referenceValue(referenceValue)
    												.calculatedValue(calculatedValue).build());
    }
    
    public UUID getClientId() {
        return clientId;
    }

    public Payroll(UUID clientId, UUID contractId, Date competence) {
        this.clientId = clientId;
        this.contractId = contractId;
        this.competence = competence;
        this.payrollDetails = new ArrayList<PayrollDetail>();
    }

    public UUID getContractId() {
        return contractId;
    }

    public Date getCompetence() {
        return competence;
    }

    public void setCompetence() {
        
    }

    public Double getInssBaseAmount() {
        return inssBaseAmount;
    }

    public void setInssBaseAmount(Double inssBaseAmount) {
        this.inssBaseAmount = inssBaseAmount;
    }

    public Double getInssBaseExcessAmount() {
        return inssBaseExcessAmount;
    }

    public void setInssBaseExcessAmount(Double inssBaseExcessAmount) {
        this.inssBaseExcessAmount = inssBaseExcessAmount;
    }

    public Double getFgtsBaseAmount() {
        return fgtsBaseAmount;
    }

    public void setFgtsBaseAmount(Double fgtsBaseAmount) {
        this.fgtsBaseAmount = fgtsBaseAmount;
    }

    public Double getIrrfBaseAmount() {
        return irrfBaseAmount;
    }

    public void setIrrfBaseAmount(Double irrfBaseAmount) {
        this.irrfBaseAmount = irrfBaseAmount;
    }

    public Double getOvertimeBaseAmount() {
        return overtimeBaseAmount;
    }

    public void setOvertimeBaseAmount(Double overtimeBaseAmount) {
        this.overtimeBaseAmount = overtimeBaseAmount;
    }

    public Double getGrossPay() {
        return grossPay;
    }

    public void setGrossPay(Double grossPay) {
        this.grossPay = grossPay;
    }

    public Double getDeductions() {
        return deductions;
    }

    public void setDeductions(Double deductions) {
        this.deductions = deductions;
    }
    
    public List<PayrollDetail> getPayrollDetails() {
		return payrollDetails;
	}

	public void sumAmount(Rubric rubric, Double calculatedValue){
        if(rubric.getType() == 'P') {
        	if(rubric.getConsistsNetPay() == 'Y') {
        		setGrossPay(getGrossPay() + calculatedValue);
        	}
        }else {
        	if(rubric.getConsistsNetPay() == 'Y') {
        		setDeductions(getDeductions() + calculatedValue);
        	}
        	
        	calculatedValue *= -1;
        }
    	
    	if(rubric.getInssBase() == 'Y') {
        	setInssBaseAmount(getInssBaseAmount() + calculatedValue);
        }
    	
    	if(rubric.getFgtsBase() == 'Y') {
    		setFgtsBaseAmount(getFgtsBaseAmount() + calculatedValue);
    	}
    	
    	if(rubric.getIrrfBase() == 'Y') {
    		setIrrfBaseAmount(getIrrfBaseAmount() + calculatedValue);
    	}
    	
    	if(rubric.getOvertimeBase() == 'Y') {
    		setOvertimeBaseAmount(getOvertimeBaseAmount() + calculatedValue);
    	}
    }
}
