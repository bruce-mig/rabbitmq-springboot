package com.github.bruce.mig.rabbitmqspringbootdemo.tut6;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;

public class Tut6Client {

    private  final RabbitTemplate template;

    private final DirectExchange exchange;

    public Tut6Client(RabbitTemplate template, DirectExchange exchange) {
        this.template = template;
        this.exchange = exchange;
    }

    int start = 0;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send(){
        System.out.println(" [x] Requesting fib(" + start + ")");
        Integer response = (Integer) template.convertSendAndReceive(
                exchange.getName(),
                "rpc",
                start++
        );
        System.out.println(" [.] Got '" + response + "'");
    }
}
