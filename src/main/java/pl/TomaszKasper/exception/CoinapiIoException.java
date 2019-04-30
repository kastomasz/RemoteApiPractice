package pl.TomaszKasper.exception;

public class CoinapiIoException extends BaseException {
    public CoinapiIoException() {
        super("Błąd po stronie coinapi.io");
    }
}
