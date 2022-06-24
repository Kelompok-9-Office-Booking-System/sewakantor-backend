package com.kampusmerdeka.officeorder.controller.admin;

import com.kampusmerdeka.officeorder.dto.request.CustomerMessageRequest;
import com.kampusmerdeka.officeorder.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/admin/conversations")
public class ConversationController {
    @Autowired
    private ChatService chatService;

    @GetMapping
    public ResponseEntity<Object> getAllConversation() {
        return chatService.getConversations();
    }

    @PostMapping
    public ResponseEntity<Object> sendMessage(@Valid @RequestBody CustomerMessageRequest request) {
        return chatService.sendMessage(request);
    }
}
