/**
 * This is the repository to interact with Currencies from the database. It extends JpaRepository for CRUD methods and
 * hence, uses JpaRepository’s methods: save(), findOne(), findById(), findAll(), count(), delete(), deleteById().
 * In addition to these methods, I define custom finder methods. It is autowired in CurrencyController. The implementation
 * is plugged in by Spring Data JPA automatically & currencies table will be generated in the H2 Database (in memory).
 *
 *  @author Esubalew A Demissie
 *
 */

package com.pharmapartners.opdracht.repository;

import com.pharmapartners.opdracht.model.Currency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    List<Currency> findByTicker(String ticker);

    List<Currency> findByName(String name);

    Page<Currency> findByTicker(String ticker, Pageable pageable);

    Page<Currency> findByName(String name, Pageable pageable);

}


