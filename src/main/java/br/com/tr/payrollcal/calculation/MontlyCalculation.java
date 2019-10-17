package br.com.tr.payrollcal.calculation;

import br.com.tr.payrollcal.model.*;

public class MontlyCalculation {
    public PayrollCalcData calculate(CalcOptions calcOptions){
        PayrollCalcData payrollCalcData = new PayrollCalcData(calcOptions);
        
        if(!payrollCalcData.retrive()) {
        	return payrollCalcData;
        }
        
        calculateHorasTrabalhadas(payrollCalcData);
        calculateInss(payrollCalcData);
        
        return payrollCalcData;
    }

    private Double calculateHorasTrabalhadas(PayrollCalcData payrollCalcData){
        Double hoursPerMonth = 0.00;
        Double calculatedValue = 0.00;
        Double salaryPerHour = 0.00;

        Contract contract = payrollCalcData.getContract();
        CalcOptions calcOptions = payrollCalcData.getCalcOptions();
        Rubric rubric = payrollCalcData.getRubricRepository().findByKey(Rubric.HORAS_TRABALHADAS);
        
        salaryPerHour = (contract.getSalary() / (contract.getHoursPerDay() * 30));
        hoursPerMonth = (contract.getHoursPerDay() * calcOptions.getMonthdays());
        calculatedValue = (salaryPerHour * hoursPerMonth);
        
        payrollCalcData.getPayroll().addDetail(rubric.getRubricId(), 220.00, calculatedValue);
        payrollCalcData.getPayroll().sumAmount(rubric, calculatedValue);
        
        return calculatedValue;
    }

    private Double calculateInss(PayrollCalcData payrollCalcData){
        Double calculatedValue = 0.00;
        
        Rubric rubric = payrollCalcData.getRubricRepository().findByKey(Rubric.INSS);        
        calculatedValue = (payrollCalcData.getPayroll().getInssBaseAmount() * 8 / 100);
        
        payrollCalcData.getPayroll().addDetail(rubric.getRubricId(), calculatedValue);
        payrollCalcData.getPayroll().sumAmount(rubric, calculatedValue);
        
        return calculatedValue;
    }
}