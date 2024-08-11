package com.example.ai.controller;

import java.util.Map;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FunctionCallingController {
    private final ChatModel chatModel;
    
    @Value("classpath:weather.st")
    private Resource weather;
    
    @GetMapping("/nofunc")
    public String noFunc(String prompt) {
    	return chatModel.call(new Prompt(prompt)).getResult().getOutput().getContent();
    }

    @GetMapping("/func")
    public String func(String prompt) {        
        return chatModel.call(new Prompt(prompt, 
				                         OpenAiChatOptions.builder()
				                         .withFunction("CurrectDateTime")
				                         .build())
        		).getResult().getOutput().getContent();
    }
}
