package br.com.tr.payrollcal.controller;

import java.util.GregorianCalendar;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.tr.payrollcal.calculation.MontlyCalculation;
import br.com.tr.payrollcal.calculation.PayrollCalcData;
import br.com.tr.payrollcal.model.CalcOptions;

@Controller
public class CalculationController {

    @RequestMapping("/payroll/calculation")
    @ResponseBody
    public String montlyCalculation() {
    	CalcOptions calcOptions = new CalcOptions.Builder()
    										.clientId(UUID.fromString("f20795ab-c818-4be9-b93a-8305e00116ae"))
    										.monthDays(30)
    										.competence(new GregorianCalendar(2019, 10, 01).getTime()).build();
    	
        MontlyCalculation montlyCalculation = new MontlyCalculation();

        PayrollCalcData payrollCalcData = montlyCalculation.calculate(calcOptions);
        return payrollCalcData.toHtml();
    }
}