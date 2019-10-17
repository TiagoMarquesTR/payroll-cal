package br.com.tr.payrollcal.calculation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.tr.payrollcal.client.ClientClient;
import br.com.tr.payrollcal.model.CalcOptions;
import br.com.tr.payrollcal.model.Client;
import br.com.tr.payrollcal.model.Contract;
import br.com.tr.payrollcal.model.Payroll;
import br.com.tr.payrollcal.model.PayrollDetail;
import br.com.tr.payrollcal.repository.RubricRepository;

public class PayrollCalcData {
	private final CalcOptions calcOptions;
	private Client client;
	private Contract contract;
	private Payroll payroll;
	private RubricRepository rubricRepository;
	
	public PayrollCalcData(CalcOptions calcOptions) {
		this.calcOptions = calcOptions;
	}
	
	public Boolean retrive() {
		if(calcOptions.getClientId() != null) {
			ClientClient clientClient = new ClientClient();
			client = clientClient.findById(calcOptions.getClientId().toString());
		}else {
			return false;
		}
		
		contract = new Contract();
		payroll = new Payroll(client.getClientId(), contract.getContractId(), calcOptions.getCompetence());
	    rubricRepository = new RubricRepository(client.getClientId());
	    
		return true;
	}

	public CalcOptions getCalcOptions() {
		return calcOptions;
	}

	public Client getClient() {
		return client;
	}

	public Contract getContract() {
		return contract;
	}

	public Payroll getPayroll() {
		return payroll;
	}

	public RubricRepository getRubricRepository() {
		return rubricRepository;
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
        
        payrollJson = client.getName() + " " + client.getClientId();
        payrollJson += toJson(payroll).get();
        return payrollJson;
    }

    public String getPayrollDetailJson() {
        String payrollDetailJson = "";

        for (PayrollDetail payrollDetail : payroll.getPayrollDetails()) {
            payrollDetailJson += toJson(payrollDetail).get();
        }

        return payrollDetailJson;
    }
    
    public String toHtml() {
    	String payrollHtml = "";
    	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    	
    	payrollHtml = "<html>";
    	payrollHtml += "ClientId: " + client.getClientId() + " Name: " + client.getName() + "<br/>";
    	payrollHtml += "Competence: " + df.format(calcOptions.getCompetence()) + "<br/><br/>";
    	
    	for (PayrollDetail payrollDetail : payroll.getPayrollDetails()) {
    		payrollHtml += "RubricId: " + payrollDetail.getRubricId()
    									+ " ReferenceValue: " + payrollDetail.getReferenceValue()
    									+ " CalculatedValue: " + payrollDetail.getCalculatedValue() + "<br/>";
        }
    	
    	payrollHtml += "<br/>Totals: <br/>" 	+ "inssBaseAmount: " + payroll.getInssBaseAmount() + " "
    									+ "inssBaseExcessAmount: " + payroll.getInssBaseExcessAmount() + " "
    									+ "fgtsBaseAmount: " + payroll.getFgtsBaseAmount() + " "
    									+ "irrfBaseAmount: " + payroll.getIrrfBaseAmount() + " "
    									+ "overtimeBaseAmount: " + payroll.getOvertimeBaseAmount() + " "
    									+ "grossPay: " + payroll.getGrossPay() + " "
    									+ "deductions: " + payroll.getDeductions() + "<br/>";
    	payrollHtml += "</html>";
    	
    	return payrollHtml;
    }
}
