package pl.TomaszKasper.service;

import pl.TomaszKasper.dto.SymbolCounterDto;
import pl.TomaszKasper.model.Trade;

import java.util.List;

public interface TradeService {

    List<Trade> getLastNumberOfTrades(Long number);

    String getTheMostFrequentSymbolIdOfLast1000Transactions();

    List<SymbolCounterDto> getTheRankingOfTransactionsOrderByFrequency();

}
