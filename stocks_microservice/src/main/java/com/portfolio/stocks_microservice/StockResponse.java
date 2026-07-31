package com.portfolio.stocks_microservice;

public record StockResponse(String ticker, String name, double price) {}