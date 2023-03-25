package com.example.Live_Stock_Updates.Models;


import com.example.Live_Stock_Updates.Enums.Frequency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Subscription {

    @Id
    private String id;
    private String stockSymbol;
    private Frequency notificationFrequency;
    private String notifyTime;

}
