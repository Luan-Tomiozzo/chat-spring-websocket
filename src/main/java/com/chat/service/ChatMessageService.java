/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.service;

import com.chat.controller.domain.ChatMessage;
import com.chat.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author luan.tomiozzo
 */

import java.util.List;

@Service
public class ChatMessageService {

    @Autowired
    private ChatMessageRepository messageRepository;

    public List<ChatMessage> findBySenderAndRecipient(String sender, String recipient) {
        
//        return messageRepository.findAll();
        
        return messageRepository.findBySenderAndRecipientValidatingEquality(sender, recipient);
    }

    public void save(ChatMessage chatMessage) {
        messageRepository.save(chatMessage);
    }
}
