package com.FinancialInsight.Engine.ratioCalculation.service;

import com.FinancialInsight.Engine.ratioCalculation.model.AnnualReportBalance;
import com.FinancialInsight.Engine.ratioCalculation.model.AnnualReportIncome;

public class ReturnOnEquityService {
    public static double calculateReturnOnEquity(AnnualReportIncome annualReportIncome, AnnualReportBalance annualReportBalance) {
        double netIncome = Double.parseDouble(annualReportIncome.getNetIncome());
        double totalShareholderEquity = Double.parseDouble(annualReportBalance.getTotalShareholderEquity());

        if (totalShareholderEquity == 0) {
            throw new ArithmeticException("Cannot calculate return on equity. Total shareholder equity is zero.");
        }

        return netIncome / totalShareholderEquity;
    }

}
