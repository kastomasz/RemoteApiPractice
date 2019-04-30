package pl.TomaszKasper.exception;

public class DataIsEmptyException extends BaseException {
    public DataIsEmptyException() {
        super("Brak danych od coinapi.io");
    }
}
