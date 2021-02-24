package com.sample.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.data.Sensor;

import com.sample.producer.sender.Sender;

@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    private Sender sender;

    @GetMapping("send/sensor/{name}")
    public ResponseEntity<String> publish(@PathVariable("name") final String name){
    	
        Sensor sensorData = new Sensor(name, String.valueOf(Math.random()*100));
        sender.sendMessageTopic(sensorData);
        
        return new ResponseEntity<String>(name, HttpStatus.OK);
        
    }

}
