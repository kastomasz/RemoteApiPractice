package pl.TomaszKasper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.TomaszKasper.model.Exchange;
import pl.TomaszKasper.service.impl.ExchangesServiceImpl;

import java.util.List;

@RestController
@RequestMapping(path = "/exchanges")
public class ExchangesController {

    @Autowired
    private ExchangesServiceImpl exchangesServiceImpl;

    @GetMapping(path = "/getAll")
    public List<Exchange> getAll() {
        final List<Exchange> result = exchangesServiceImpl.getAllExchanges();
        return result;
    }
}

// localhost:8080/exchanges/getAll
