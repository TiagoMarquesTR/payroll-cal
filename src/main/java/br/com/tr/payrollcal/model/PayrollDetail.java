package br.com.tr.payrollcal.model;

import java.util.Date;
import java.util.UUID;

public class PayrollDetail {
    private final UUID clientId;
    private final UUID contractId;
    private final UUID rubricId;
    private final Date competence;
    private Double referenceValue = 0.00;
    private Double calculatedValue = 0.00;

    public static class Builder{
        private final UUID clientId;
        private final UUID contractId;
        private final UUID rubricId;
        private final Date competence;
        private Double referenceValue = 0.00;
        private Double calculatedValue = 0.00;

        public Builder(UUID clientId, UUID contractId, UUID rubricId, Date competence){
            this.clientId = clientId;
            this.contractId = contractId;
            this.rubricId = rubricId;
            this.competence = competence;
        }

        public Builder referenceValue(Double referenceValue){
            this.referenceValue = referenceValue;
            return this;
        }

        public Builder calculatedValue(Double calculatedValue){
            this.calculatedValue = calculatedValue;
            return this;
        }

        public PayrollDetail build(){
            PayrollDetail payrollDetail = new PayrollDetail(this.clientId, this.contractId, this.rubricId, this.competence);
            payrollDetail.setReferenceValue(this.referenceValue);
            payrollDetail.setCalculatedValue(this.calculatedValue);
            return payrollDetail;
        }
    }

    public PayrollDetail(UUID clientId, UUID contractId, UUID rubricId, Date competence){
        this.clientId = clientId;
        this.contractId = contractId;
        this.rubricId = rubricId;
        this.competence = competence;
    }

    public UUID getClientId() {
        return clientId;
    }

    public UUID getContractId() {
        return contractId;
    }

    public UUID getRubricId() {
        return rubricId;
    }

    public Date getCompetence() {
        return competence;
    }

    public Double getReferenceValue() {
        return referenceValue;
    }

    public void setReferenceValue(Double referenceValue) {
        this.referenceValue = referenceValue;
    }

    public Double getCalculatedValue() {
        return calculatedValue;
    }

    public void setCalculatedValue(Double calculatedValue) {
        this.calculatedValue = calculatedValue;
    }
}
