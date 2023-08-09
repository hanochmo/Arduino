package com.ruoyi.chatgpt.domain;


import lombok.Data;

@Data
public class ChatCompletionChoice {

    Integer index;

    ChatMessage message;

    String finishReason;

}
