package br.com.tr.payrollcal.calculation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.GregorianCalendar;

import br.com.tr.payrollcal.model.*;

public class MontlyCalculation {
    private final Client client = new Client();
    private final Contract contract = new Contract();
    private final List<Rubric> rubrics = new ArrayList<Rubric>();

    private final Integer monthDays = 30;
    private final Date competence = new GregorianCalendar(2019, 10, 01).getTime();

    private Payroll payroll = new Payroll(client.getClientId(), contract.getContractId(), competence);
    private List<PayrollDetail> payrollDetails = new ArrayList<PayrollDetail>();

    public void calculate(){
        Double calculatedValue = 0.00;

        rubrics.add(new Rubric("Horas Trabalhadas"));
        rubrics.add(new Rubric("INSS"));
        rubrics.add(new Rubric("IRRF"));
        rubrics.add(new Rubric("FGTS"));

        calculatedValue = calculateHorasTrabalhadas();
        payroll.setInssBaseAmount(payroll.getInssBaseAmount() + calculatedValue);
        payroll.setFgtsBaseAmount(payroll.getFgtsBaseAmount() + calculatedValue);
        payroll.setIrrfBaseAmount(payroll.getIrrfBaseAmount() + calculatedValue);
        payroll.setOvertimeBaseAmount(payroll.getOvertimeBaseAmount() + calculatedValue);
        payroll.setGrossPay(payroll.getGrossPay() + calculatedValue);
        payrollDetails.add(new PayrollDetail.Builder(client.getClientId(), contract.getContractId(),
                                                     rubrics.get(0).getRubricId(), competence)
                                                    .referenceValue(220.00)
                                                    .calculatedValue(calculatedValue).build());
        calculatedValue = 0.00;

        calculatedValue = calculateInss();
        payroll.setIrrfBaseAmount(payroll.getIrrfBaseAmount() - calculatedValue);
        payroll.setDeductions(payroll.getDeductions() + calculatedValue);
        payrollDetails.add(new PayrollDetail.Builder(client.getClientId(), contract.getContractId(),
                                                     rubrics.get(0).getRubricId(), competence)
                                                    .calculatedValue(calculatedValue).build());
        calculatedValue = 0.00;
    }

    public Double calculateHorasTrabalhadas(){
        Double hoursPerMonth = 0.00;
        Double horasTrabalhadas = 0.00;
        Double salaryPerHour = (contract.getSalary() / (contract.getHoursPerDay() * 30));

        hoursPerMonth = (contract.getHoursPerDay() * monthDays);
        horasTrabalhadas = (salaryPerHour * hoursPerMonth);
        return horasTrabalhadas;
    }

    public Double calculateInss(){
        Double inss = 0.00;

        inss = (payroll.getInssBaseAmount() * 8 / 100);
        return inss;
    }

    public static Optional<String> toJson(Object objectClass){
        ObjectMapper mapper = new ObjectMapper();
		
		try {
			return Optional.ofNullable(mapper.writeValueAsString(objectClass));
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Is not a valid object class!");
			return Optional.empty();
		}
	}

    public String getPayrollJson() {
        String payrollJson = "";

        payrollJson = "<html>";
        payrollJson += toJson(payroll).get() + "<br />";
        payrollJson += "</html>";
        return payrollJson;
    }

    public String getPayrollDetailJson() {
        String payrollDetailJson = "";

        payrollDetailJson = "<html>";
        for (PayrollDetail payrollDetail : payrollDetails) {
            payrollDetailJson += toJson(payrollDetail).get() + "<br />";
        }
        payrollDetailJson += "</html>";

        return payrollDetailJson;
    }
}