package com.FinancialInsight.Engine.ratioCalculation.service;


import com.FinancialInsight.Engine.ratioCalculation.model.AnnualReport;
import com.FinancialInsight.Engine.ratioCalculation.model.FinancialStatement;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.apache.commons.lang3.StringUtils;


import java.io.IOException;
import java.util.List;

@Service
public class FinancialStatementService {
    @Value("${alphavantage.api.key}")
    private String apiKey;
    private static final String ALPHA_VANTAGE_API_BASE_URL = "https://www.alphavantage.co/query";
    private static final String FUNCTION_OVERVIEW = "BALANCE_SHEET";

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

        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        FinancialStatement financialStatement;
        try {
            financialStatement = objectMapper.readValue(jsonResponse, FinancialStatement.class);
        } catch (JsonProcessingException e){
            throw new RuntimeException("Error parsing JSON response from API", e);
        }

        // Perform any necessary processing or transformations on the financial statement data

        return financialStatement;
    }
    public AnnualReport findAnnualReportForYear(List<AnnualReport> annualReports, String year) {


        for (AnnualReport annualReport : annualReports) {
           System.out.println(StringUtils.substring(annualReport.getFiscalDateEnding(), 0, 4));
           System.out.println(year);
            if (StringUtils.substring(annualReport.getFiscalDateEnding(), 0, 4).equals(year)) {
                System.out.println("gottem");
                return annualReport;
            }
        }
            return null;
    }
    //Answer this question .equals works for string yes o no?

}
