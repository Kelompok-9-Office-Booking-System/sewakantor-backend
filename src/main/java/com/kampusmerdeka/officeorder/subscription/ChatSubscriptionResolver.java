package com.kampusmerdeka.officeorder.subscription;

import com.kampusmerdeka.officeorder.entity.Message;
import com.kampusmerdeka.officeorder.service.AuthService;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class ChatSubscriptionResolver implements GraphQLSubscriptionResolver {
    @Autowired
    private AuthService authService;

    @Autowired
    private Flux<Message> messageEvents;

    @PreAuthorize("isAuthenticated()")
    public Publisher<Message> customerChats() {
        return messageEvents.filter(message -> message.getConversation().getCustomer().equals(authService.me()));
    }
}
