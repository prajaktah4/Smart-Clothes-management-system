package com.rewear.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rewear.entity.Exchange;
import com.rewear.repository.ExchangeRepository;

@Service
public class ExchangeService {

    @Autowired
    private ExchangeRepository exchangeRepository;
    
    
    public long getTotalExchanges() {
        return exchangeRepository.count();
    }

    public Exchange saveExchange(Exchange exchange) {
        return exchangeRepository.save(exchange);
    }

    public List<Exchange> getAllRequests() {
        return exchangeRepository.findAll();
    }

    public Exchange getRequest(Long id) {
        return exchangeRepository.findById(id).orElse(null);
    }

    public void deleteRequest(Long id) {
        exchangeRepository.deleteById(id);
    }

}