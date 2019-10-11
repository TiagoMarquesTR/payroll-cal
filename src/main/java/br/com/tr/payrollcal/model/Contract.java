package br.com.tr.payrollcal.model;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

public class Contract {
	private final UUID contractId = UUID.randomUUID();
	private final Date admission = new GregorianCalendar(2019, 01, 01).getTime();
	private final Double hoursPerDay = 7.333333;
	private final Double salary = 1000.00;

	public Date getAdmission() {
		return admission;
	}

	public Double getHoursPerDay() {
		return hoursPerDay;
	}

	public Double getSalary() {
		return salary;
	}

	public UUID getContractId() {
		return contractId;
	}
}
