package com.example.Live_Stock_Updates.Controllers;


import com.example.Live_Stock_Updates.Models.StockDailyData;
import com.example.Live_Stock_Updates.Services.ScheduledTask;
import com.example.Live_Stock_Updates.Services.StockDailyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class StockDailyDataController {

    @Autowired
    private StockDailyDataService stockDailyDataService;

    @Autowired
    ScheduledTask scheduledTask;

    @PostMapping("/stocks/{symbol}/save")
    public List<StockDailyData> saveStockData(@PathVariable String symbol) {
        return stockDailyDataService.saveStockData(symbol);
    }


    @GetMapping("/stocks/{symbol}/get-daily")
    public List<StockDailyData> getStockData(@PathVariable String symbol) {
        return stockDailyDataService.getStockData(symbol);
    }

    @GetMapping("/stocks/get")
    public List<StockDailyData> getStocksByDateAndSymbol(
                @RequestParam("start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                @RequestParam("end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                @RequestParam("stock_symbol") String stockSymbol) {

            return stockDailyDataService.getStocksByDateAndSymbol(startDate, endDate, stockSymbol);
    }


}

