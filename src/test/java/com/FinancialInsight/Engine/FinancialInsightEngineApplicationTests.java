package com.FinancialInsight.Engine;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
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
		String symbol = "AAPL";
		String year = "2022";

		ResponseEntity<String> response = restTemplate.getForEntity(
				baseUrl + "/debt-to-equity/" + symbol + "/" + year, String.class);
		System.out.println("DEBT TO EQUITY: "+ response.getBody() +"\n");
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void testCurrentRatio() {
		String symbol = "AAPL";
		String year = "2022";

		ResponseEntity<String> response = restTemplate.getForEntity(
				baseUrl + "/current-ratio/" + symbol + "/" + year, String.class);
		System.out.println("CURRENT RATIO: "+ response.getBody() +"\n");

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void testReturnOnEquity() {
		String symbol = "AAPL";
		String year = "2022";

		ResponseEntity<String> response = restTemplate.getForEntity(
				baseUrl + "/Return-on-Equity/" + symbol + "/" + year, String.class);
		System.out.println("RETURN ON EQUITY: "+ response.getBody() +"\n");

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void testProfitMargin() {
		String symbol = "AAPL";
		String year = "2022";

		ResponseEntity<String> response = restTemplate.getForEntity(
				baseUrl + "/profit-margin/" + symbol + "/" + year, String.class);
		System.out.println("PROFIT MARGIN: "+ response.getBody() +"\n");

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
