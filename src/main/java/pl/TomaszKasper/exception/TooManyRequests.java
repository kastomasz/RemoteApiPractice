package pl.TomaszKasper.exception;

public class TooManyRequests extends BaseException {
    public TooManyRequests() {
        super("Przekroczono limit zapytań do coinapi.io");
    }
}
