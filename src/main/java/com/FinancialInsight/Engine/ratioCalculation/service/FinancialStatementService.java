package com.FinancialInsight.Engine.ratioCalculation.service;


import com.FinancialInsight.Engine.ratioCalculation.model.AnnualReportBalance;
import com.FinancialInsight.Engine.ratioCalculation.model.FinancialStatement;
import com.FinancialInsight.Engine.ratioCalculation.model.Report;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.apache.commons.lang3.StringUtils;



import java.util.List;

@Service
public class FinancialStatementService {
    @Value("${alphavantage.api.key}")
    private String apiKey;
    private static final String ALPHA_VANTAGE_API_BASE_URL = "https://www.alphavantage.co/query";


    private String buildApiUrl(String symbol, String functionOverview){
        return ALPHA_VANTAGE_API_BASE_URL +
                "?function=" + functionOverview +
                "&symbol=" + symbol +
                "&apikey=" + apiKey;
    }

    private final RestTemplate restTemplate;

    @Autowired
    public FinancialStatementService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    private ObjectMapper objectMapper;


    public FinancialStatement getFinancialStatement(String symbol, String functioOverveiew) {
        String apiUrl = buildApiUrl(symbol,functioOverveiew);

        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);
        FinancialStatement financialStatement;
        try {
            financialStatement = objectMapper.readValue(jsonResponse, FinancialStatement.class);
        } catch (JsonProcessingException e){
            throw new RuntimeException("Error parsing JSON response from API", e);
        }
        return financialStatement;
    }
    public Report findAnnualReportForYear(List<Report> annualReports, String year) {


        for (Report annualReport : annualReports) {
            if (StringUtils.substring(annualReport.fiscalDateEnding(), 0, 4).equals(year)) {
                return annualReport;
            }
        }
            return null;
    }



}
