package com.kampusmerdeka.officeorder.repository;

import com.kampusmerdeka.officeorder.entity.Message;
import com.kampusmerdeka.officeorder.entity.UserCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Iterable<Message> findByConversationAndIsReadFalse(UserCustomer userCustomer);

    Iterable<Message> findByConversationId(Long id);
}