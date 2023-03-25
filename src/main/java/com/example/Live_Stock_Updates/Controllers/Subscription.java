package com.example.Live_Stock_Updates.Controllers;


import com.example.Live_Stock_Updates.Services.ScheduledTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Subscription {

    @Autowired
    ScheduledTask scheduledTask;

    @GetMapping("/stocks/subscribe")
    public String subscribeStock(@RequestParam String symbol,@RequestParam String email){
        return scheduledTask.sendEmailDaily(symbol,email);
    }
}
