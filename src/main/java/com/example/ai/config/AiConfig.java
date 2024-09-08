package com.example.ai.config;

import org.springframework.ai.model.function.FunctionCallback;
import org.springframework.ai.model.function.FunctionCallbackWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.ai.function.CurrectDateTimeFunction;
import com.example.ai.function.WeatherService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AiConfig {	
	@Autowired
	private WeatherService weatherService;
	
    @Bean
    public FunctionCallback currectDateTime() {
        return FunctionCallbackWrapper.builder(new CurrectDateTimeFunction())
                .withName("CurrectDateTime")
                .withDescription("Get the Date Time")
                .withResponseConverter((response) -> response.currDateTime().toString())
                .build();
    }
	
	@Bean
    public FunctionCallback currectWeather() {
        return FunctionCallbackWrapper.builder(weatherService)
                .withName("CurrectWeather")
                .withDescription("Get Currect Weather")
                .build();
    }
}
