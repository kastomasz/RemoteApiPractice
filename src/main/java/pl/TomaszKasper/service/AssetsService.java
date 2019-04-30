package pl.TomaszKasper.service;

import pl.TomaszKasper.model.Asset;

import java.util.List;

public interface AssetsService {

    List<Asset> getAllAssets();

    Asset getRandomAsset();

}
