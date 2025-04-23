package com.example.yoga;

import java.util.List;
// Fix 4: Match OpenAI API structure
public class ChatRequest {
    private String model;
    private List<ChatMessage> messages;
    private double temperature;

    // Change from ChatMessage[] to List<ChatMessage>
    public ChatRequest(String model, List<ChatMessage> messages, double temperature) {
        this.model = model;
        this.messages = messages;
        this.temperature = temperature;
    }

    // Keep getters
}
