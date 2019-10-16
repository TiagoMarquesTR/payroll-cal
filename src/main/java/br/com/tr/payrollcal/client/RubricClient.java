package br.com.tr.payrollcal.client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.tr.payrollcal.model.Rubric;

public class RubricClient {
	RestTemplate restTemplate = new RestTemplate();

	public List<Rubric> getAll() {
		ResponseEntity<List<Rubric>> response = restTemplate.exchange("http://localhost:8080/v1/rubric", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Rubric>>() {});
		List<Rubric> rubrics = response.getBody();
		return rubrics;
	}
}
