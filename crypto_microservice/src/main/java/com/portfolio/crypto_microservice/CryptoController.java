package com.portfolio.crypto_microservice;

import com.portfolio.crypto_microservice.Crypto;
import com.portfolio.crypto_microservice.CryptoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class CryptoController {

    private final CryptoRepository cryptoRepository;

    // Injecting the isolated H2 database repository
    public CryptoController(CryptoRepository cryptoRepository) {
        this.cryptoRepository = cryptoRepository;
    }

    @GetMapping("/api/crypto")
    public List<Crypto> getCrypto() {
        return cryptoRepository.findAll(); // Reads directly from the local cryptodb H2 instance
    }
}