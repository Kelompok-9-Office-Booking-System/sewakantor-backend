package com.kampusmerdeka.officeorder.repository;

import com.kampusmerdeka.officeorder.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
}