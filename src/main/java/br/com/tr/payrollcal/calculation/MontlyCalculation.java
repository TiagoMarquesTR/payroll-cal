package br.com.tr.payrollcal.calculation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.GregorianCalendar;

import br.com.tr.payrollcal.client.ClientClient;
import br.com.tr.payrollcal.model.*;
import br.com.tr.payrollcal.repository.RubricRepository;

public class MontlyCalculation {
    private ClientClient clientClient = new ClientClient();
    private final Client client = clientClient.findById("f20795ab-c818-4be9-b93a-8305e00116ae"); //private final Client client = new Client();
    private final Contract contract = new Contract();

    private final Integer monthDays = 30;
    private final Date competence = new GregorianCalendar(2019, 10, 01).getTime();

    private Rubric rubric;
    private Payroll payroll = new Payroll(client.getClientId(), contract.getContractId(), competence);
    private List<PayrollDetail> payrollDetails = new ArrayList<PayrollDetail>();
 
    public void calculate(){
        Double calculatedValue = 0.00;
	           
        RubricRepository rubricRepository = new RubricRepository(client.getClientId());
		
        rubric = rubricRepository.findByKey(Rubric.HORAS_TRABALHADAS);
        calculatedValue = calculateHorasTrabalhadas();
        sumAmount(rubric, calculatedValue);
        payrollDetails.add(new PayrollDetail.Builder(client.getClientId(), contract.getContractId(), rubric.getRubricId(), competence)
                                                    .referenceValue(220.00)
                                                    .calculatedValue(calculatedValue).build());
        calculatedValue = 0.00;

        rubric = rubricRepository.findByKey(Rubric.INSS);
        calculatedValue = calculateInss();
        sumAmount(rubric, calculatedValue);
        payrollDetails.add(new PayrollDetail.Builder(client.getClientId(), contract.getContractId(), rubric.getRubricId(), competence)
                                                    .calculatedValue(calculatedValue).build());
        calculatedValue = 0.00;
    }

    private Double calculateHorasTrabalhadas(){
        Double hoursPerMonth = 0.00;
        Double horasTrabalhadas = 0.00;
        Double salaryPerHour = (contract.getSalary() / (contract.getHoursPerDay() * 30));

        hoursPerMonth = (contract.getHoursPerDay() * monthDays);
        horasTrabalhadas = (salaryPerHour * hoursPerMonth);
        return horasTrabalhadas;
    }

    private Double calculateInss(){
        Double inss = 0.00;

        inss = (payroll.getInssBaseAmount() * 8 / 100);
        return inss;
    }

    private void sumAmount(Rubric rubric, Double calculatedValue){
        switch(rubric.getName()){
            case "Horas Trabalhadas":
                payroll.setInssBaseAmount(payroll.getInssBaseAmount() + calculatedValue);
                payroll.setFgtsBaseAmount(payroll.getFgtsBaseAmount() + calculatedValue);
                payroll.setIrrfBaseAmount(payroll.getIrrfBaseAmount() + calculatedValue);
                payroll.setOvertimeBaseAmount(payroll.getOvertimeBaseAmount() + calculatedValue);
                payroll.setGrossPay(payroll.getGrossPay() + calculatedValue);

            case "INSS":
                payroll.setIrrfBaseAmount(payroll.getIrrfBaseAmount() - calculatedValue);
                payroll.setDeductions(payroll.getDeductions() + calculatedValue);
        }
    }

    public static Optional<String> toJson(Object object){
        ObjectMapper mapper = new ObjectMapper();
		
		try {
			return Optional.ofNullable(mapper.writeValueAsString(object));
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Is not a valid object class!");
			return Optional.empty();
		}
	}

    public String getPayrollJson() {
        String payrollJson = "";
        
        payrollJson = "<html>";
        payrollJson += client.getName() + " " + client.getClientId() + "<br />";
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