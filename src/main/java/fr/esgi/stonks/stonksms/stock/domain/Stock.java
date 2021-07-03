package fr.esgi.stonks.stonksms.stock.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Stock {
    private final String currency;
    private final Float value;

    @Override
    public String toString() {
        return "Stock{" +
                "currency='" + currency + '\'' +
                ", value=" + value +
                '}';
    }
}
