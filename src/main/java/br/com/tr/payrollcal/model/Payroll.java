package br.com.tr.payrollcal.model;

import java.util.Date;
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

    public UUID getClientId() {
        return clientId;
    }

    public Payroll(UUID clientId, UUID contractId, Date competence) {
        this.clientId = clientId;
        this.contractId = contractId;
        this.competence = competence;
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
}
