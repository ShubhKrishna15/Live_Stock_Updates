package com.example.Live_Stock_Updates.Repositories;

import com.example.Live_Stock_Updates.Models.StockDailyData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository

public interface StockDailyDataRepository extends MongoRepository<StockDailyData, Integer> {

    List<StockDailyData> findBySymbol(String symbol);


    List<StockDailyData> findBySymbolAndDateBetween(String symbol, LocalDate startDate, LocalDate endDate);
}