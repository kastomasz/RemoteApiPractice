package pl.TomaszKasper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.TomaszKasper.dto.SymbolCounterDto;
import pl.TomaszKasper.model.Trade;
import pl.TomaszKasper.service.impl.TradeServiceImpl;

import java.util.List;

@RestController
@RequestMapping(path = "/trades")
public class TradesController {

    @Autowired
    private TradeServiceImpl tradeServiceImpl;

    @GetMapping
    public List<Trade> getLastNumberOfTrades(@RequestParam(value = "limit") Long limit) {
        final List<Trade> result = tradeServiceImpl.getLastNumberOfTrades(limit);
        return result;
    }

    @GetMapping(path = "/theMostFrequent")
    public String getTheMostFrequentSymbolIdOfLast1000Transactions() {
        final String result = tradeServiceImpl.getTheMostFrequentSymbolIdOfLast1000Transactions();
        return result;
    }

    @GetMapping(path = "/getRank")
    public List<SymbolCounterDto> getRank() {
        final List<SymbolCounterDto> result = tradeServiceImpl.getTheRankingOfTransactionsOrderByFrequency();
        return result;
    }
}

// localhost:8080/trades/getRank

// localhost:8080/trades/theMostFrequent
