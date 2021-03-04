package com.pharmapartners.opdracht;

import com.pharmapartners.opdracht.model.Currency;
import com.pharmapartners.opdracht.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JPAUnitTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    CurrencyRepository repository;

    @Test
    public void should_find_no_currencies_if_repository_is_empty() {
        Iterable<Currency> currencies = repository.findAll();

        assertThat(currencies).isNotEmpty();
    }


    @Test
    public void should_store_a_currency() {

        Currency currency = repository.save(new Currency("Currency ticker", "Currency name",1000, 1234 ));

        assertThat(currency).hasFieldOrPropertyWithValue("ticker", "Currency ticker");
        assertThat(currency).hasFieldOrPropertyWithValue("name", "Currency name");
        assertThat(currency).hasFieldOrPropertyWithValue("number_of_coins", 1000);
        assertThat(currency).hasFieldOrPropertyWithValue("market_cap",  1234);

    }

    @Test
    public void should_find_all_currencies() {
        Currency currency1 = new Currency("ticker1", "name1", 1000,   1234 );
        entityManager.persist(currency1);

        Currency currency2 = new Currency("ticker2", "name2", 2000,   2234);
        entityManager.persist(currency2);

        Currency currency3 =new Currency("ticker3", "name3", 3000,   3234 );
        entityManager.persist(currency3);

        Currency currency4 =new Currency("ticker4", "name4", 4000,  4234 );
        entityManager.persist(currency4);

        Iterable<Currency> currencies = repository.findAll();

        /* The additional 10 records are from data.sql
         */
        assertThat(currencies).hasSize(4 + 10).contains(currency1, currency2, currency3,currency4);
    }

    @Test
    public void should_find_currency_by_id() {
        Currency currency1 = new Currency("ticker1", "name#1", 1000,  1234 );
        entityManager.persist(currency1);

        Currency currency2 = new Currency("ticker2", "name#2", 2000,  2234 );
        entityManager.persist(currency2);


        Currency foundCurrency = repository.findById(currency2.getId()).get();

        assertThat(foundCurrency).isEqualTo(currency2);
    }

    @Test
    public void should_find_currencies_by_ticker() {
        Currency currency1 = new Currency("ticker1", "name1", 1000,  1234 );
        entityManager.persist(currency1);

        Currency currency2 = new Currency("ticker2", "name2", 2000,  2234 );
        entityManager.persist(currency2);

        Currency currency3 =new Currency("ticker1", "name3", 3000,  3234 );
        entityManager.persist(currency3);

        Currency currency4 =new Currency("ticker4", "name4", 4000,  4234 );
        entityManager.persist(currency4);

        Iterable<Currency> currencies = repository.findByTicker("ticker1");

        assertThat(currencies).hasSize(2).contains(currency1, currency3);
    }

    @Test
    public void should_find_currencies_by_name() {
        Currency currency1 = new Currency("ticker1", "name1", 1000,  1234 );
        entityManager.persist(currency1);

        Currency currency2 = new Currency("ticker2", "name2", 2000,  2234 );
        entityManager.persist(currency2);

        Currency currency3 =new Currency("ticker3", "name3", 3000,  3234 );
        entityManager.persist(currency3);

        Currency currency4 =new Currency("ticker4", "name2", 4000,  4234  );
        entityManager.persist(currency4);

        Iterable<Currency> currencies = repository.findByName("name2");

        assertThat(currencies).hasSize(2).contains(currency2, currency4);
    }




    @Test
    public void should_update_currency_by_id() {
        Currency currency1 = new Currency("ticker1", "name#1", 1000,  1234);
        entityManager.persist(currency1);

        Currency currency2 = new Currency("ticker2", "name#2", 2000,  2234 );
        entityManager.persist(currency2);

        Currency updatedCurrency = new Currency("updated ticker2", "updated name#2", 5000, 5234 );

        Currency currency = repository.findById(currency2.getId()).get();

        currency.setTicker(updatedCurrency.getTicker());
        currency.setName(updatedCurrency.getName());
        currency.setNumber_of_coins(updatedCurrency.getNumber_of_coins());
        currency.setMarket_cap(updatedCurrency.getMarket_cap());

        repository.save(currency);

        Currency checkCurrency = repository.findById(currency2.getId()).get();

        assertThat(checkCurrency.getId()).isEqualTo(currency2.getId());
        assertThat(checkCurrency.getTicker()).isEqualTo(updatedCurrency.getTicker());
        assertThat(checkCurrency.getName()).isEqualTo(updatedCurrency.getName());
        assertThat(checkCurrency.getNumber_of_coins()).isEqualTo(updatedCurrency.getNumber_of_coins());
        assertThat(checkCurrency.getMarket_cap()).isEqualTo(updatedCurrency.getMarket_cap());

    }

    @Test
    public void should_delete_currency_by_id() {
        Currency currency1 = new Currency("ticker1", "name#1", 1000,  1234 );
        entityManager.persist(currency1);

        Currency currency2 = new Currency("ticker2", "name#2", 2000,  2234 );
        entityManager.persist(currency2);

        Currency currency3 = new Currency("ticker3", "name#3", 3000,  3234);
        entityManager.persist(currency3);

        Currency currency4 = new Currency("ticker4", "name#4", 4000,  4234 );
        entityManager.persist(currency4);

        repository.deleteById(currency3.getId());

        Iterable<Currency> currencies = repository.findAll();

        /* The additional 10 records are from data.sql
         */

        assertThat(currencies).hasSize(3 + 10).contains(currency1, currency2, currency4);
    }

    @Test
    public void should_delete_all_currencies() {
        repository.deleteAll();
        Iterable<Currency> currencies = repository.findAll();
        assertThat(currencies).isEmpty();
    }
}
