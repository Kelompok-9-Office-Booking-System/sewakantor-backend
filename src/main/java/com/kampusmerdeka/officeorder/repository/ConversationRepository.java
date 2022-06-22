package com.kampusmerdeka.officeorder.repository;

import com.kampusmerdeka.officeorder.dto.repsonse.ConversationResponse;
import com.kampusmerdeka.officeorder.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
        @Query("SELECT new com.kampusmerdeka.officeorder.dto.repsonse.ConversationResponse(" +
            "c.id, " +
            "MAX(m.text), " +
            "CONCAT(c.customer.firstName, ' ', c.customer.lastName), " +
            "c.customer.avatar, " +
            "CAST(SUM(CASE WHEN m.isRead = FALSE THEN 1 ELSE 0 END) AS int), " +
            "MAX(m.createdAt)" +
            ") " +
            "FROM Conversation c " +
            "INNER JOIN Message m ON m.conversation = c " +
            "GROUP BY c, c.customer.firstName, c.customer.lastName, c.customer.avatar")
    List<ConversationResponse> getConversations();
}