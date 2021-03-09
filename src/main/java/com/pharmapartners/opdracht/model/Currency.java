/**
 * Hero is the main entity we'll be using for our application & has four fields: ticker, name, number_of_coins and
 * market_cap.
 *
 * @author Esubalew A. Demissie
 *
 */

package com.pharmapartners.opdracht.model;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "currencies")
public class Currency implements  Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ticker")
    private String ticker;

    @Column(name = "name")
    private String name;

    @Column(name = "number_of_coins")
    private int number_of_coins;

    @Column(name = "market_cap")
    private int market_cap;

    public Currency() {

    }
    public Currency(String ticker, String name, int number_of_coins, int market_cap) {
        this.ticker = ticker;
        this.name = name;
        this.number_of_coins = number_of_coins;
        this.market_cap = market_cap;
    }

    public long getId() {
        return id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber_of_coins() {
        return number_of_coins;
    }

    public void setNumber_of_coins(int number_of_coins) {
        this.number_of_coins = number_of_coins;
    }

    public int getMarket_cap() {
        return market_cap;
    }

    public void setMarket_cap(int market_cap) {
        this.market_cap = market_cap;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", ticker='" + ticker + '\'' +
                ", name='" + name + '\'' +
                ", number_of_coins=" + number_of_coins +
                ", market_cap=" + market_cap +
                '}';
    }
}
