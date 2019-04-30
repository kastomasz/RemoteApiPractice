package pl.TomaszKasper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.TomaszKasper.model.Asset;
import pl.TomaszKasper.service.impl.AssetsServiceImpl;

import java.util.List;

@RestController
@RequestMapping(path = "/assets")
public class AssetsController {

    @Autowired
    private AssetsServiceImpl assetsServiceImpl;

    @GetMapping(path = "/getAll")
    public List<Asset> getAll() {
        final List<Asset> response = assetsServiceImpl.getAllAssets();
        return response;
    }

    @GetMapping(path = "/random")
    public Asset random() {
        final Asset result = assetsServiceImpl.getRandomAsset();
        return result;
    }
}

// localhost:8080/assets/getAll
// localhost:8080/assets/random
