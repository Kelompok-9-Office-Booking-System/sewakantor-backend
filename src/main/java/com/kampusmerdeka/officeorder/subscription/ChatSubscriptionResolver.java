package com.kampusmerdeka.officeorder.subscription;

import com.kampusmerdeka.officeorder.dto.repsonse.MessageResponse;
import com.kampusmerdeka.officeorder.entity.Message;
import com.kampusmerdeka.officeorder.entity.UserCustomer;
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
    private Flux<MessageResponse> messageEvents;

    @PreAuthorize("isAuthenticated()")
    public Publisher<MessageResponse> customerChats() {
        UserCustomer me = authService.me();
        System.out.println("this is me " + me.getEmail());
        return messageEvents;
    }
}
