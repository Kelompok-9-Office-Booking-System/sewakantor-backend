package com.kampusmerdeka.officeorder.config;

import com.kampusmerdeka.officeorder.entity.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.util.concurrent.Queues;

@Configuration
public class SubscriptionConfig {

  @Bean
  public Sinks.Many<Message> messageSinks() {
    return Sinks.many().multicast().onBackpressureBuffer(Queues.SMALL_BUFFER_SIZE, false);
  }

  @Bean
  public Flux<Message> messageFlux(Sinks.Many<Message> messageSinks) {
    return messageSinks.asFlux();
  }

}