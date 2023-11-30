package com.sheroozdrive.SheroozDrive.service;

import com.sheroozdrive.SheroozDrive.model.dto.FileDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class EnqueueDequeService {

    private final AmqpTemplate amqpTemplate;


    @Value("${rabbitmq.exchange}")
    String exchange;

    @Value("${rabbitmq.routingkey}")
    private String routingkey;

    public EnqueueDequeService(RabbitTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void publishMessage(FileDto customerDto){
        amqpTemplate.convertAndSend(exchange,routingkey,customerDto);
    }

}
