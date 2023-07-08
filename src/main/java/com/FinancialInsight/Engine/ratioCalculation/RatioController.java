package com.FinancialInsight.Engine.ratioCalculation;
import com.FinancialInsight.Engine.ratioCalculation.model.AnnualReportBalance;
import com.FinancialInsight.Engine.ratioCalculation.model.AnnualReportCashFlow;
import com.FinancialInsight.Engine.ratioCalculation.model.AnnualReportIncome;
import com.FinancialInsight.Engine.ratioCalculation.model.FinancialStatement;
import com.FinancialInsight.Engine.ratioCalculation.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class RatioController {
    private final FinancialStatementService financialStatementService;

    @Autowired
    public RatioController(FinancialStatementService financialStatementService, DebtToEquityService debtToEquityService, CurrentRatioService currentRatioService) {
        this.financialStatementService = financialStatementService;
    }

    @GetMapping(value="/debt-to-equity/{symbol}/{year}")
    public ResponseEntity<String> debtToEquity(@PathVariable String symbol,@PathVariable String year) throws IOException {
        FinancialStatement financialStatement = financialStatementService.getFinancialStatement(symbol, "BALANCE_SHEET");
        if (financialStatement.getAnnualReports().isEmpty()){
            return  ResponseEntity.ok("Statement not found");
        }
        AnnualReportBalance report =(AnnualReportBalance) financialStatementService.findAnnualReportForYear(financialStatement.getAnnualReports(),year);
        double ratio = DebtToEquityService.calculateDebtToEquityRatio(report);

        return ResponseEntity.ok(String.valueOf(ratio));
    }

    @GetMapping(value= "/current-ratio/{symbol}/{year}")
    public ResponseEntity<String> currentRatio(@PathVariable String symbol,@PathVariable String year) throws IOException {
        FinancialStatement financialStatement = financialStatementService.getFinancialStatement(symbol,"BALANCE_SHEET");
        if (financialStatement.getAnnualReports().isEmpty()){
            return  ResponseEntity.ok("Statement not found");
        }
        AnnualReportBalance report = (AnnualReportBalance) financialStatementService.findAnnualReportForYear(financialStatement.getAnnualReports(),year);
        double ratio = CurrentRatioService.calculateCurrentRatio(report);

        return ResponseEntity.ok(String.valueOf(ratio));
    }
    @GetMapping(value="/Return-on-Equity/{symbol}/{year}")
    public ResponseEntity<String> returnOnEquity(@PathVariable String symbol,@PathVariable String year) throws IOException {
        FinancialStatement financialStatementBalance = financialStatementService.getFinancialStatement(symbol,"BALANCE_SHEET");
        FinancialStatement financialStatementIncome = financialStatementService.getFinancialStatement(symbol,"INCOME_STATEMENT");
        if (financialStatementBalance.getAnnualReports().isEmpty() || financialStatementIncome.getAnnualReports().isEmpty()){
            return  ResponseEntity.ok("Statement not found");
        }
        AnnualReportIncome IncomeReport = (AnnualReportIncome) financialStatementService.findAnnualReportForYear(financialStatementIncome.getAnnualReports(),year);
        AnnualReportBalance BalanceReport = (AnnualReportBalance) financialStatementService.findAnnualReportForYear(financialStatementBalance.getAnnualReports(),year);
        double ratio = ReturnOnEquityService.calculateReturnOnEquity(IncomeReport,BalanceReport);

        return ResponseEntity.ok(String.valueOf(ratio));
    }

    @GetMapping(value="/profit-margin/{symbol}/{year}")
    public ResponseEntity<String> profitMargin(@PathVariable String symbol,@PathVariable String year) throws IOException {
        FinancialStatement financialStatement = financialStatementService.getFinancialStatement(symbol,"CASH_FLOW");

        if (financialStatement.getAnnualReports().isEmpty() ){
            return  ResponseEntity.ok("Statement not found");
        }
        AnnualReportCashFlow Report = (AnnualReportCashFlow) financialStatementService.findAnnualReportForYear(financialStatement.getAnnualReports(),year);
        double ratio = ProfitMarginService.calculateProfitMargin(Report);

        return ResponseEntity.ok(String.valueOf(ratio));
    }



    @GetMapping(value="/financial-statement/{symbol}/{year}/{functionOverview}")
    public ResponseEntity<String> financialStatement (@PathVariable String symbol,@PathVariable String year, @PathVariable String functionOverview) throws IOException {
        FinancialStatement financialStatement = financialStatementService.getFinancialStatement(symbol,functionOverview);
        if (financialStatement.getAnnualReports().isEmpty()){
            return  ResponseEntity.ok("Statement not found");
        }
        AnnualReportBalance report = (AnnualReportBalance) financialStatementService.findAnnualReportForYear(financialStatement.getAnnualReports(),year);
        return ResponseEntity.ok(report.toString());
    }
}