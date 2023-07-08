package com.FinancialInsight.Engine.ratioCalculation.service;

import com.FinancialInsight.Engine.ratioCalculation.model.AnnualReportCashFlow;

public class ProfitMarginService {

    public static double calculateProfitMargin(AnnualReportCashFlow annualReportCashFlow) {
        double netIncome = Double.parseDouble(annualReportCashFlow.getNetIncome());
        double revenue = Double.parseDouble(annualReportCashFlow.getProfitLoss());
        return netIncome / revenue;
    }
}
