package com.example.Live_Stock_Updates.Services;


import com.example.Live_Stock_Updates.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ScheduledTask {


      //  private static final String API_URL = "https://your-api-url.com";
     //   private static final String EMAIL_RECIPIENT = "recipient-email@example.com";

        @Autowired
        private JavaMailSender mailSender;

        @Autowired
        UserRepository userRepository;

        @Scheduled(cron = "0 0 12 * * ?") // runs at 12:00 PM every day
        public String sendEmailDaily(String symbol,String email) {
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject("localhost:8181/stocks/"+symbol+"/save", String.class);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("API Response");
            message.setText(response);

            mailSender.send(message);
            return "You are subscribed successfully";
        }
    }
//
//
//}
