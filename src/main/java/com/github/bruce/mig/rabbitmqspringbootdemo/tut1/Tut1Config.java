package com.github.bruce.mig.rabbitmqspringbootdemo.tut1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.amqp.core.Queue;

/**
 * @author Bruce Migeri
 */

@Profile({"tut1","hello-world"})
@Configuration
public class Tut1Config {

    @Bean
    public Queue hello() {
        return new Queue("hello");
    }

    @Profile("receiver")
    @Bean
    public Tut1Receiver receiver() {
        return new Tut1Receiver();
    }

    @Profile("sender")
    @Bean
    public Tut1Sender sender() {
        return new Tut1Sender();
    }

}
