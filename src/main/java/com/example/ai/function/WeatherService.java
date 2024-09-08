package com.example.ai.function;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

@Service
public class WeatherService implements Function<WeatherService.Request, WeatherService.Response>{

    private final RestTemplate restTemplate;
    
    private final String apiUrl = "https://opendata.cwa.gov.tw/api/v1/rest/datastore/O-A0003-001";
    
    @Value("${CWA_API_KEY}")
    private String apiKey;
    
    
    public WeatherService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    
	public JsonNode getFullWeatherData() {
		String fullUrl = apiUrl + "?Authorization=" + apiKey;
        try {
            JsonNode response = restTemplate.getForObject(fullUrl, JsonNode.class);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	@Override
	public Response apply(Request request) {
		return new Response(getFullWeatherData());
	}
	public record Request(String State){
	}
	public record Response(JsonNode currWehther) {
	}
}
