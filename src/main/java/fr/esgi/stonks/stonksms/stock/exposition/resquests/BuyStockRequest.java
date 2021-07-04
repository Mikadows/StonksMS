package fr.esgi.stonks.stonksms.stock.exposition.resquests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BuyStockRequest {
    private String currency;
    private Float value;
}
