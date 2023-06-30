package com.FinancialInsight.Engine.ratioCalculation.service;

import com.FinancialInsight.Engine.ratioCalculation.model.AnnualReport;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class DebtToEquityService {

    public static double calculateDebtToEquityRatio(AnnualReport annualReport) throws IOException {

        double totalLiabilities = Double.parseDouble(annualReport.getTotalLiabilities());
        double totalShareholderEquity = Double.parseDouble(annualReport.getTotalShareholderEquity());

        if (totalShareholderEquity == 0) {
            throw new ArithmeticException("Cannot calculate debt-to-equity ratio. Total shareholder equity is zero.");
        }

        return totalLiabilities / totalShareholderEquity;
    }

}
