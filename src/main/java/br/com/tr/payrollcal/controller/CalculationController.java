package br.com.tr.payrollcal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.tr.payrollcal.calculation.MontlyCalculation;

@Controller
public class CalculationController {

    @RequestMapping("/payroll/calculation")
    @ResponseBody
    public String hello() {
        MontlyCalculation montlyCalculation = new MontlyCalculation();

        montlyCalculation.calculate();
        return montlyCalculation.getPayrollJson() + montlyCalculation.getPayrollDetailJson();
        //return "Horas trabalhadas = " + String.format("%1$,.2f", montlyCalculation.calculateHorasTrabalhadas());
    }
}