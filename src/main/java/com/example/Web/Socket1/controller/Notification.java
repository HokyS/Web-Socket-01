package com.example.Web.Socket1.controller;

import com.example.Web.Socket1.entities.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class Notification {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/notification")
    public ResponseEntity sendNotificationToClients(@RequestBody DTO message) {
        simpMessagingTemplate.convertAndSend("/broadcast/messages", message);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @MessageMapping("/hello")
    public void handleMessageFromWebSocket(DTO message){
        System.out.println("Arrived something on /app/hello" );
    }
}