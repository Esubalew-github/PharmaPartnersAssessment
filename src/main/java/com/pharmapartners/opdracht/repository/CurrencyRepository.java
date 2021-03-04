package com.pharmapartners.opdracht.repository;

import com.pharmapartners.opdracht.model.Currency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CurrencyRepository extends JpaRepository<Currency, Long> , CrudRepository<Currency, Long> {

    List<Currency> findByTicker(String ticker);

    List<Currency> findByName(String name);

    Page<Currency> findByTicker(String ticker, Pageable pageable);

    Page<Currency> findByName(String name, Pageable pageable);

}


