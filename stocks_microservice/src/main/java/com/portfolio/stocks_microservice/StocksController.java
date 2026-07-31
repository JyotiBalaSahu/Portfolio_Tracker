package com.portfolio.stocks_microservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class StocksController {

    private final StockRepository stockRepository;

    // Injecting the H2 database repository via constructor dependency injection
    public StocksController(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @GetMapping("/api/stocks")
    public List<Stock> getStocks() {
        return stockRepository.findAll(); // Reads directly from the local stocksdb H2 instance
    }
}