package com.portfolio.bff_gateway;

import com.portfolio.bff_gateway.StockData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class DashboardController {

    private final WebClient webClientStocks;
    private final WebClient webClientCrypto;

    public DashboardController(WebClient.Builder webClientBuilder) {
        this.webClientStocks = webClientBuilder.baseUrl("http://localhost:8081").build();
        this.webClientCrypto = webClientBuilder.baseUrl("http://localhost:8082").build();
    }

    @GetMapping("/api/dashboard")
    public Mono<Map<String, Object>> getDashboardData() {

        // Fetch Stocks safely
        Mono<List<StockData>> stocksMono = webClientStocks.get()
                .uri("/api/stocks")
                .retrieve()
                .bodyToFlux(StockData.class)
                .collectList() // Collect into a list first
                // Catch network crashes, timeouts, or 404/500 errors here
                .onErrorResume(throwable -> {
                    System.out.println("Stocks Service Fallback Triggered: " + throwable.getMessage());
                    return Mono.just(List.of()); // Returns a clean empty array gracefully
                });

        // Fetch Crypto safely
        Mono<List<CryptoData>> cryptoMono = webClientCrypto.get()
                .uri("/api/crypto")
                .retrieve()
                .bodyToFlux(CryptoData.class)
                .collectList() // Collect into a list first
                // Catch network crashes, timeouts, or 404/500 errors here
                .onErrorResume(throwable -> {
                    System.out.println("Crypto Service Fallback Triggered: " + throwable.getMessage());
                    return Mono.just(List.of()); // Returns a clean empty array gracefully
                });

        // Zip guarantees parallel execution, while individual fallbacks ensure uptime
        return Mono.zip(stocksMono, cryptoMono).map(tuple -> {
            Map<String, Object> aggregatedMap = new HashMap<>();
            aggregatedMap.put("stocks", tuple.getT1());
            aggregatedMap.put("crypto", tuple.getT2());
            return aggregatedMap;
        });
    }


}