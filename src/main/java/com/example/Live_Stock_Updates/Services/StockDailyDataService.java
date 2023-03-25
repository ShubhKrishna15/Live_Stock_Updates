package com.example.Live_Stock_Updates.Services;

import com.example.Live_Stock_Updates.Models.StockDailyData;
import com.example.Live_Stock_Updates.Repositories.StockDailyDataRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



@Service
public class StockDailyDataService {

    private int StockId=0;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StockDailyDataRepository stockDailyDataRepository;

    @Value("${alphavantage.apikey}")
    private String apiKey;


    public List<StockDailyData> saveStockData(String symbol) {
        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=" + symbol + "&apikey=" + apiKey;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode rootNode = mapper.readTree(responseEntity.getBody());
            JsonNode dailyDataNode = rootNode.get("Time Series (Daily)");
            Iterator<Map.Entry<String, JsonNode>> dailyDataIterator = dailyDataNode.fields();
            while (dailyDataIterator.hasNext()) {
                Map.Entry<String, JsonNode> entry = dailyDataIterator.next();
                LocalDate date = LocalDate.parse(entry.getKey(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                JsonNode dailyData = entry.getValue();
                StockDailyData stockDailyData = new StockDailyData();
                stockDailyData.setSymbol(symbol);
                stockDailyData.setDate(date);
                stockDailyData.setOpen(BigDecimal.valueOf(dailyData.get("1. open").asDouble()));
                stockDailyData.setHigh(BigDecimal.valueOf(dailyData.get("2. high").asDouble()));
                stockDailyData.setLow(BigDecimal.valueOf(dailyData.get("3. low").asDouble()));
                stockDailyData.setClose(BigDecimal.valueOf(dailyData.get("4. close").asDouble()));
                stockDailyData.setVolume((long) dailyData.get("6. volume").asDouble());
                StockId = stockDailyData.getID();
                StockId++;
                stockDailyData.setID(StockId);
                stockDailyDataRepository.save(stockDailyData);

//                return stockDailyDataRepository.findBySymbol(symbol);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stockDailyDataRepository.findBySymbol(symbol);

    }

    public List<StockDailyData> getStockData(String symbol) {
        return stockDailyDataRepository.findBySymbol(symbol);
    }

    public List<StockDailyData> getStocksByDateAndSymbol(LocalDate startDate, LocalDate endDate, String stockSymbol) {
        return stockDailyDataRepository.findBySymbolAndDateBetween(stockSymbol, startDate, endDate);
    }

}



