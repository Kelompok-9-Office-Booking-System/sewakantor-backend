package com.kampusmerdeka.officeorder.controller.customer;

import com.kampusmerdeka.officeorder.dto.request.CustomerMessageRequest;
import com.kampusmerdeka.officeorder.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/customer/messages")
public class MessageController {
    @Autowired
    private ChatService chatService;

    @GetMapping
    public ResponseEntity<Object> getAllMessages() {
        return chatService.getMessages();
    }

    @PostMapping
    public ResponseEntity<Object> sendMessage(@Valid @RequestBody CustomerMessageRequest request) {
        return chatService.sendMessage(request);
    }
}
