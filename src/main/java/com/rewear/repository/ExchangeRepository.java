package com.rewear.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rewear.entity.Exchange;

public interface ExchangeRepository extends JpaRepository<Exchange,Long>{

    List<Exchange> findByRequesterEmail(String requesterEmail);

}