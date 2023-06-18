package com.FinancialInsight.Engine.ratioCalculation.service;


import com.FinancialInsight.Engine.ratioCalculation.model.FinancialStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class FinancialStatementService {
    @Value("${alphavantage.api.key}")
    private String apiKey;
    private static final String ALPHA_VANTAGE_API_BASE_URL = "https://www.alphavantage.co/query";
    private static final String FUNCTION_OVERVIEW = "OVERVIEW";

    private String buildApiUrl(String symbol) {
        return ALPHA_VANTAGE_API_BASE_URL +
                "?function=" + FUNCTION_OVERVIEW +
                "&symbol=" + symbol +
                "&apikey=" + apiKey;
    }

    private final RestTemplate restTemplate;

    @Autowired
    public FinancialStatementService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public FinancialStatement getFinancialStatements(String symbol) {
        String apiUrl = buildApiUrl(symbol);

        FinancialStatement financialStatement = restTemplate.getForObject(apiUrl, FinancialStatement.class);

        // Perform any necessary processing or transformations on the financial statement data

        return financialStatement;
    }

}
