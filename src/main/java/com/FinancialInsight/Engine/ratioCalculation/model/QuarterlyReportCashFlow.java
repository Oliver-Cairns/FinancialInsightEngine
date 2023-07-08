package com.FinancialInsight.Engine.ratioCalculation.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuarterlyReportCashFlow implements Report{
    private String fiscalDateEnding;
    private String reportedCurrency;
    private String operatingCashflow;
    private String paymentsForOperatingActivities;
    private String proceedsFromOperatingActivities;
    private String changeInOperatingLiabilities;
    private String changeInOperatingAssets;
    private String depreciationDepletionAndAmortization;
    private String capitalExpenditures;
    private String changeInReceivables;
    private String changeInInventory;
    private String profitLoss;
    private String cashflowFromInvestment;
    private String cashflowFromFinancing;
    private String proceedsFromRepaymentsOfShortTermDebt;
    private String paymentsForRepurchaseOfCommonStock;
    private String paymentsForRepurchaseOfEquity;
    private String paymentsForRepurchaseOfPreferredStock;
    private String dividendPayout;
    private String dividendPayoutCommonStock;
    private String dividendPayoutPreferredStock;
    private String proceedsFromIssuanceOfCommonStock;
    private String proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet;
    private String proceedsFromIssuanceOfPreferredStock;
    private String proceedsFromRepurchaseOfEquity;
    private String proceedsFromSaleOfTreasuryStock;
    private String changeInCashAndCashEquivalents;
    private String changeInExchangeRate;
    private String netIncome;

    @Override
    public String fiscalDateEnding() {
        return fiscalDateEnding;
    }
}
