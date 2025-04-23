package com.example.yoga;

public class ChatMessage {
    private final String role; // "user" or "assistant"
    private final String content;

    public ChatMessage(String role, String content) {
        this.role = role;
        this.content = content;
    }

    // Getters
    public String getContent() {
        return content;
    }

    public String getRole() {
        return role;
    }

    // Helper method for adapter
    public boolean isUser() {
        return "user".equals(role);
    }
}