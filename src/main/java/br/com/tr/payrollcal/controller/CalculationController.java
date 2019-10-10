package br.com.tr.payrollcal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CalculationController {

    @RequestMapping("/payroll/calculation")
    @ResponseBody
    public String hello() {
        return "Hello World";
    }
}