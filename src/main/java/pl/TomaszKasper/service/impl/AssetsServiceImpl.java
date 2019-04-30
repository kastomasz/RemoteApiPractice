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
import pl.TomaszKasper.model.Asset;
import pl.TomaszKasper.service.AssetsService;

import java.util.List;
import java.util.Random;

@Service
public class AssetsServiceImpl implements AssetsService {

    @Value("${coinapi.coreURL}")
    private String baseUrl;

    @Value(("${coinapi.apiKey}"))
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    public List<Asset> getAllAssets() {
        final List<Asset> result;

        final String url = baseUrl + "assets" + "?apikey=" + apiKey;
        ResponseEntity<List<Asset>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Asset>>() {
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

    public Asset getRandomAsset() {
        Random random = new Random();
        List<Asset> assets = getAllAssets();
        return assets.get(random.nextInt(assets.size()));
    }

}
