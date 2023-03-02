/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.repository;

import com.chat.controller.domain.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author luan.tomiozzo
 */

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findBySenderAndRecipient(String sender, String recipient);

    default List<ChatMessage> findBySenderAndRecipientValidatingEquality(String sender, String recipient) {
        if (sender.equals(recipient)) {
            return findAll();
        }
        return findBySenderAndRecipient(sender, recipient);
    }
}
