package com.FinancialInsight.Engine.ratioCalculation.service;

import com.FinancialInsight.Engine.ratioCalculation.model.AnnualReportBalance;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CurrentRatioService {
    public static double calculateCurrentRatio(AnnualReportBalance annualReport) throws IOException {

        double currentAssets = Double.parseDouble(annualReport.getTotalCurrentAssets());
        double currentLiabilities = Double.parseDouble(annualReport.getTotalCurrentLiabilities());

        if (currentLiabilities == 0) {
            throw new ArithmeticException("Cannot calculate current ratio. Current total liabilities is zero.");
        }
        return currentAssets / currentLiabilities;
    }

}
