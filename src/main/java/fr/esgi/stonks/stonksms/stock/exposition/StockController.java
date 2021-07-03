package fr.esgi.stonks.stonksms.stock.exposition;

import fr.esgi.stonks.stonksms.stock.domain.Stock;
import fr.esgi.stonks.stonksms.stock.exposition.resquests.BuyStockRequest;
import fr.esgi.stonks.stonksms.stock.infra.MessageProducer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/stock")
public class StockController {
    private final MessageProducer messageProducer;

    @PostMapping(value = "/buy")
    public ResponseEntity<?> buyStock(@RequestBody BuyStockRequest request){
        Stock stock = new Stock(request.getCurrency(), request.getValue());
        this.messageProducer.sendMessage(stock.toString());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
