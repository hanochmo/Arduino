package com.ruoyi.chatgpt.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ChatCompletionRequest {


    String model;

    List<ChatMessage> messages;

    Double temperature;

    Integer n;

    Boolean stream;

    List<String> stop;

    Integer max_tokens;

    String user;




}
