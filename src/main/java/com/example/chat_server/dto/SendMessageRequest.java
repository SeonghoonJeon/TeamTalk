package com.example.chat_server.dto;

public class SendMessageRequest {
    private String messageDetail; // message.message_detail

    public String getMessageDetail() { return messageDetail; }
    public void setMessageDetail(String messageDetail) { this.messageDetail = messageDetail; }
}