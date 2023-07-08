package com.FinancialInsight.Engine;

import com.FinancialInsight.Engine.ratioCalculation.model.Report;
import com.FinancialInsight.Engine.ratioCalculation.model.ReportDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Report.class, new ReportDeserializer());
        mapper.registerModule(module);
        return mapper;
    }
}
