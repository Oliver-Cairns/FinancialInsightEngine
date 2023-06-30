package com.FinancialInsight.Engine;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FinancialInsightEngineApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String baseUrl;

	@BeforeEach
	public void setUp() {
		baseUrl = "http://localhost:" + port;
	}

	@Test
	public void testDebtToEquity() {
		// Assuming symbol and year as parameters
		String symbol = "AAPL";
		String year = "2022";

		ResponseEntity<String> response = restTemplate.getForEntity(
				baseUrl + "/debt-to-equity/" + symbol + "/" + year, String.class);
		System.out.println(response.getBody());

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
