package com.example.ai.function;

import java.util.Date;
import java.util.function.Function;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public class CurrectDateTimeFunction implements Function<CurrectDateTimeFunction.Request, CurrectDateTimeFunction.Response>{
	@Override
	public Response apply(Request request) {
		return new Response(new Date());
	}
	public record Request(String State){
	}
	public record Response(Date currDateTime) {
	}
}
