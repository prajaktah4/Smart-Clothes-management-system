package com.rewear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.rewear.entity.Exchange;
import com.rewear.entity.Product;
import com.rewear.service.ExchangeService;
import com.rewear.service.ProductService;

@Controller
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @Autowired
    private ProductService productService;

    // Show Exchange Products
    @GetMapping("/exchange")
    public String exchange(Model model) {

        model.addAttribute("products",
                productService.getExchangeProducts());

        return "exchange-clothes";
    }

    // Open Exchange Request Form
    @GetMapping("/exchange/request/{id}")
    public String exchangeRequest(@PathVariable Long id,
                                  Model model) {

        Product product = productService.getProductById(id);

        Exchange exchange = new Exchange();
        exchange.setProduct(product);

        model.addAttribute("exchange", exchange);

        return "exchange-request";
    }

    // Save Exchange Request
    @PostMapping("/saveExchange")
    public String saveExchange(@ModelAttribute Exchange exchange) {

        exchange.setExchangeStatus("Pending");

        exchangeService.saveExchange(exchange);

        return "redirect:/my-exchanges";
    }

    // My Exchange Requests
    @GetMapping("/my-exchanges")
    public String myExchanges(Model model) {

        model.addAttribute("requests",
                exchangeService.getAllRequests());

        return "my-exchanges";
    }

    // Delete Request
    @GetMapping("/deleteExchange/{id}")
    public String deleteExchange(@PathVariable Long id) {

        exchangeService.deleteRequest(id);

        return "redirect:/my-exchanges";
    }

}