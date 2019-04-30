package pl.TomaszKasper.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException {
    private String message;
}
