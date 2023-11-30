package com.sheroozdrive.SheroozDrive.controller;

import com.sheroozdrive.SheroozDrive.model.dto.FileDto;
import com.sheroozdrive.SheroozDrive.service.EnqueueDequeService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/mq")
public class RabbitMQTestController {

    private final EnqueueDequeService enqueueDequeService;

    public RabbitMQTestController(EnqueueDequeService enqueueDequeService) {
        this.enqueueDequeService = enqueueDequeService;
    }

    @PostMapping("/publish")
    public String publishMessage(@RequestBody FileDto customerDto) {
        enqueueDequeService.publishMessage(customerDto);
        return "message saved";
    }

}
