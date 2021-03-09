/**
 * This is the controller that provides APIs for creating, retrieving, updating, deleting and finding Currency.
 * It is a RestController which has request mapping methods for RESTful requests such as: getAllCurrencies,
 * createCurrency, updateCurrency, deleteCurrency, findByTicker, findByName and so on.
 *
 * @author Esubalew A Demissie
 *
 */

package com.pharmapartners.opdracht.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.pharmapartners.opdracht.model.Currency;
import com.pharmapartners.opdracht.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CurrencyController {


 @Autowired
 CurrencyRepository currencyRepository;

 private Sort.Direction getSortDirection(String direction) {

       if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
 }

     @GetMapping("/currencies")

    public ResponseEntity<Map<String, Object>> getAllCurrenciesPage(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id,desc") String[] sort) {


        try {
            List<Order> orders = new ArrayList<Order>();

            // will sort more than 2 fields
            // sortOrder="field, direction"
            for(int i = 0; i< sort.length; i++) {
             if (sort[i].contains(",")) {
                 for (String sortOrder : sort) {
                  String[] _sort = sortOrder.split(",");
                  orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
                 }
             } else {
                 //sortDirection=default
              orders.add(new Order(getSortDirection("desc"), sort[0]));
                 }
            }

            List<Currency> currencies = new ArrayList<Currency>();
            Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

            Page<Currency> pageCurrencies;
            if (name == null)
                pageCurrencies = currencyRepository.findAll(pagingSort);
            else

            pageCurrencies = currencyRepository.findByName(name, pagingSort);

            Map<String, Object> response = new HashMap<>();
            response.put("currencies",  pageCurrencies.getContent());
            response.put("currentPage", pageCurrencies.getNumber());
            response.put("totalItems", pageCurrencies.getTotalElements());
            response.put("totalPages", pageCurrencies.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/currencies/{id}")
    public ResponseEntity<Currency> getCurrencyById(@PathVariable("id") long id) {
        Optional<Currency> currencyData = currencyRepository.findById(id);

        if (currencyData.isPresent()) {
            return new ResponseEntity<>(currencyData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/currencies")
    public ResponseEntity<Currency> createCurrency(@RequestBody Currency currency) {
        try {
            Currency _currency = currencyRepository.save(new Currency(currency.getTicker(), currency.getName(), currency.getNumber_of_coins(), currency.getMarket_cap()));
            return new ResponseEntity<>(_currency, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/currencies/{id}")
    public ResponseEntity<Currency> updateCurrency(@PathVariable("id") long id, @RequestBody Currency currency) {
        Optional<Currency> currencyData = currencyRepository.findById(id);

        if (currencyData.isPresent()) {
            Currency _currency = currencyData.get();
            _currency.setTicker(currency.getTicker());
            _currency.setName(currency.getName());
            _currency.setNumber_of_coins(currency.getNumber_of_coins());
            _currency.setTicker(currency.getTicker());
            return new ResponseEntity<>(currencyRepository.save(_currency), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/currencies/{id}")
    public ResponseEntity<HttpStatus> deleteCurrency(@PathVariable("id") long id) {
        try {
            currencyRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/currencies")
    public ResponseEntity<HttpStatus> deleteAllCurrencies() {
        try {
            currencyRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
