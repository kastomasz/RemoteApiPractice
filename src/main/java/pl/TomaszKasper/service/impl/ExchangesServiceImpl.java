package pl.TomaszKasper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.TomaszKasper.exception.CoinapiIoException;
import pl.TomaszKasper.exception.DataIsEmptyException;
import pl.TomaszKasper.exception.TooManyRequests;
import pl.TomaszKasper.model.Exchange;
import pl.TomaszKasper.service.ExchangeService;

import java.util.List;

@Service
public class ExchangesServiceImpl implements ExchangeService {

    @Value("${coinapi.coreURL}")
    private String baseUrl;

    @Value(("${coinapi.apiKey}"))
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    public List<Exchange> getAllExchanges() {
        final List<Exchange> result;

        final String url = baseUrl + "exchanges" + "?apikey=" + apiKey;
        ResponseEntity<List<Exchange>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Exchange>>() {
                });
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
}
