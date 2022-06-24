package com.kampusmerdeka.officeorder.controller.admin;

import com.kampusmerdeka.officeorder.dto.repsonse.ConversationResponse;
import com.kampusmerdeka.officeorder.dto.repsonse.MessageResponse;
import com.kampusmerdeka.officeorder.dto.request.AdminMessageRequest;
import com.kampusmerdeka.officeorder.dto.request.CustomerMessageRequest;
import com.kampusmerdeka.officeorder.entity.UserCustomer;
import com.kampusmerdeka.officeorder.service.AuthService;
import com.kampusmerdeka.officeorder.service.GraphQLChatService;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Sinks;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
public class GraphQLConversationController {

    @Autowired
    private GraphQLChatService chatService;
    @Autowired
    private AuthService authService;
    @Autowired
    private Sinks.Many<MessageResponse> messageSinks;
    @Autowired
    private Flux<MessageResponse> messageEvents;
    private FluxSink<ConversationResponse> conversationStream;
    private ConnectableFlux<ConversationResponse> conversationPublisher;

    private FluxSink<MessageResponse> messageStream;
    private ConnectableFlux<MessageResponse> messagePublisher;

    @PostConstruct
    public void init() {

        Flux<ConversationResponse> publisherConversation = Flux.create(emitter -> {
            conversationStream = emitter;
        });
        conversationPublisher = publisherConversation.publish();
        conversationPublisher.connect();

        Flux<MessageResponse> publisherMessage = Flux.create(emitter -> {
            messageStream = emitter;
        });
        messagePublisher = publisherMessage.publish();
        messagePublisher.connect();
    }

    @SchemaMapping(typeName = "Query", field = "conversations")
    public List<ConversationResponse> getAllEmployees() {
        return chatService.getConversations();
    }

    @MutationMapping(name = "adminSendMessage")
    public MessageResponse adminSendMessage(@Argument Long conversationId, @Argument String text) {
        MessageResponse response = chatService.sendMessage(AdminMessageRequest.builder().conversationId(conversationId).text(text).build());
        messageSinks.tryEmitComplete();

        return response;
    }

    @MutationMapping(name = "customerSendMessage")
    public MessageResponse customerSendMessage(@Argument String text) {
        MessageResponse response = chatService.sendMessage(CustomerMessageRequest.builder().text(text).build());
        messageSinks.tryEmitComplete();

        return response;
    }
    @PreAuthorize("isAuthenticated()")
    public Publisher<MessageResponse> customerChats() {
        UserCustomer me = authService.me();
        System.out.println("this is me " + me.getEmail());
        return messageEvents;
    }
//    @SubscriptionMapping(name = "customerChats")
//    @PreAuthorize("isAuthenticated()")
//    public Publisher<MessageResponse> customerChats() {
//        return messagePublisher;
//    }
}