package com.FinancialInsight.Engine.ratioCalculation.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class ReportDeserializer extends JsonDeserializer<Report> {

    @Override
    public Report deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        if (node.has("grossProfit") || node.has("totalRevenue")) {
            return jp.getCodec().treeToValue(node, AnnualReportIncome.class);
        }

        if (node.has("totalAssets") || node.has("totalCurrentAssets")) {
            return jp.getCodec().treeToValue(node, AnnualReportBalance.class);
        }

        if (node.has("profitLoss") || node.has("netIncome")) {
            return jp.getCodec().treeToValue(node, AnnualReportCashFlow.class);
        }

        throw new IOException("Unable to determine concrete class for AnnualReport");
    }
}

