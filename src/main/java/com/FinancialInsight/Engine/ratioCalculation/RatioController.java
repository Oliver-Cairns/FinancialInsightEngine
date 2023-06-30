package com.FinancialInsight.Engine.ratioCalculation;

import com.FinancialInsight.Engine.ratioCalculation.model.AnnualReport;
import com.FinancialInsight.Engine.ratioCalculation.model.FinancialStatement;
import com.FinancialInsight.Engine.ratioCalculation.service.DebtToEquityService;
import com.FinancialInsight.Engine.ratioCalculation.service.FinancialStatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;



@RestController
public class RatioController {
    private final FinancialStatementService financialStatementService;
    private final DebtToEquityService debtToEquityService;

    @Autowired
    public RatioController(FinancialStatementService financialStatementService, DebtToEquityService debtToEquityService) {
        this.financialStatementService = financialStatementService;
        this.debtToEquityService = debtToEquityService;
    }

    @GetMapping(value="/debt-to-equity/{symbol}/{year}")
    public ResponseEntity<String> debtToEquity(@PathVariable String symbol,@PathVariable String year) throws IOException {
        FinancialStatement financialStatement = financialStatementService.getFinancialStatements(symbol);
        if (financialStatement.getAnnualReports().isEmpty()){
            return  ResponseEntity.ok("Statement not found");

        }
        AnnualReport report = financialStatementService.findAnnualReportForYear(financialStatement.getAnnualReports(),year);
        double ratio = DebtToEquityService.calculateDebtToEquityRatio(report);

        return ResponseEntity.ok(String.valueOf(ratio));
    }
}
