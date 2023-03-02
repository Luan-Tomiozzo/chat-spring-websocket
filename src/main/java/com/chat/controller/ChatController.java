/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.controller;

import com.chat.controller.domain.ChatMessage;
import com.chat.service.ChatMessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



/**
 *
 * @author luan.tomiozzo
 */
@RestController
@RequestMapping("/chat")
@Tag(name = "Chat")
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ChatMessageService chatMessageService;

    @PostMapping("/message")
    @Operation(summary = "Envia uma mensagem de chat")
    public ResponseEntity<Void> sendMessage(@RequestParam(name = "sender", required = true) String sender, @RequestParam(name = "recipient", required = true) String recipient, @RequestParam(name = "content", required = true) String content) {
        ChatMessage message = new ChatMessage(sender, recipient, content, LocalDateTime.now());
        chatMessageService.save(message);
        messagingTemplate.convertAndSendToUser(recipient, "/queue/messages", message);
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/messages")
//    @Operation(summary = "Recupera as mensagens trocadas entre dois usuários")
//    public ResponseEntity<List<ChatMessage>> getMessages(@RequestParam(name = "sender", required = true) String sender, @RequestParam(name = "recipient", required = true) String recipient) {
//        List<ChatMessage> messages = chatMessageService.findBySenderAndRecipient(sender, recipient);
//        return ResponseEntity.ok(messages);
//    }
    @GetMapping("/messages")
    @Operation(summary = "Recupera as mensagens trocadas entre dois usuários")
    public ResponseEntity<List<ChatMessage>> getMessages(
            @RequestParam String sender,
            @RequestParam String recipient) {
        List<ChatMessage> messages = chatMessageService.findBySenderAndRecipient(sender, recipient);
        return ResponseEntity.ok(messages);
    }
}
