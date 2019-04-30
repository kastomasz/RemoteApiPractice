package pl.TomaszKasper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.TomaszKasper.dto.SymbolCounterDto;
import pl.TomaszKasper.exception.CoinapiIoException;
import pl.TomaszKasper.exception.DataIsEmptyException;
import pl.TomaszKasper.exception.TooManyRequests;
import pl.TomaszKasper.model.Trade;
import pl.TomaszKasper.service.TradeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TradeServiceImpl implements TradeService {

    @Value("${coinapi.coreURL}")
    private String baseUrl;

    @Value(("${coinapi.apiKey}"))
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    public List<Trade> getLastNumberOfTrades(Long number) {
        final List<Trade> result;

        final String url = baseUrl + "trades/latest?limit=" + Long.toString(number) + "&apikey=" + apiKey;
        System.out.println("TESTSTS");
        ResponseEntity<List<Trade>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Trade>>() {
                });
        System.out.println("Response=" + response.getStatusCode());
        result = response.getBody();
        if(response.getStatusCode().equals(HttpStatus.TOO_MANY_REQUESTS)) {
            throw new TooManyRequests();
        }
        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            throw new CoinapiIoException();
        }
        if (result.isEmpty()) {
            throw new DataIsEmptyException();
        }
        return result;
    }

    public String getTheMostFrequentSymbolIdOfLast1000Transactions() {
        List<Trade> trades = getLastNumberOfTrades(1000L);
        Map<String, Long> symbolsMap = new HashMap<>();
        Long winnerCounter = 0L;
        String winnerSymbolId = "";
        for (Trade trade : trades) {
            String symbolId = trade.getSymbol_id();
            if(symbolsMap.containsKey(symbolId)) {
                Long value = symbolsMap.get(symbolId);
                symbolsMap.put(symbolId, value + 1L);
            } else {
                symbolsMap.put(symbolId, 1L);
            }
            if (symbolsMap.get(symbolId) > winnerCounter) {
                winnerCounter = symbolsMap.get(symbolId);
                winnerSymbolId = symbolId;
            }
        }
        return winnerSymbolId + ": " + Long.toString(winnerCounter);
    }

    public List<SymbolCounterDto> getTheRankingOfTransactionsOrderByFrequency() {
        final List<SymbolCounterDto> result;
        List<Trade> trades = getLastNumberOfTrades(1000L);
        Map<String, Long> symbolsMap = new HashMap<>();
        Long winnerCounter = 0L;
        String winnerSymbolId = "";
        for (Trade trade : trades) {
            String symbolId = trade.getSymbol_id();
            if(symbolsMap.containsKey(symbolId)) {
                Long value = symbolsMap.get(symbolId);
                symbolsMap.put(symbolId, value + 1L);
            } else {
                symbolsMap.put(symbolId, 1L);
            }
        }
        List<SymbolCounterDto> temporaryList = new ArrayList<>();
        for (String key : symbolsMap.keySet()) {
            SymbolCounterDto dtoToAdd = SymbolCounterDto.builder()
                    .symbol_id(key)
                    .counter(symbolsMap.get(key))
                    .build();
            temporaryList.add(dtoToAdd);
        }
        temporaryList.sort((o1, o2) -> o2.getCounter().compareTo(o1.getCounter()));
        result = temporaryList;
        return result;
    }
}
