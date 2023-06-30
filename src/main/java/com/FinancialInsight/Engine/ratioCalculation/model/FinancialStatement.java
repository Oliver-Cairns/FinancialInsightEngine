package com.FinancialInsight.Engine.ratioCalculation.model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class FinancialStatement {
    private String symbol;
    private List<AnnualReport> annualReports;
    private List<QuarterlyReport> quarterlyReports;

}
