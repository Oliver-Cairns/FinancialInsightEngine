package com.FinancialInsight.Engine.ratioCalculation.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


public interface  Report {
    String fiscalDateEnding();
}
