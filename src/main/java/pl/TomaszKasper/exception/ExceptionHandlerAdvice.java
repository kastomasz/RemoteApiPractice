package pl.TomaszKasper.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.TomaszKasper.dto.ErrorDto;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    public static final Map<Class, HttpStatus> errorMap;
    static {
        errorMap = new HashMap<>();
        errorMap.put(DataIsEmptyException.class, HttpStatus.BAD_REQUEST);
        errorMap.put(CoinapiIoException.class, HttpStatus.I_AM_A_TEAPOT);
        errorMap.put(TooManyRequests.class, HttpStatus.TOO_MANY_REQUESTS);
    }

    @ExceptionHandler
    @ResponseBody
    ResponseEntity<ErrorDto> handle(Exception exception, HttpServletRequest req) {

        String eventId = UUID.randomUUID().toString();
        String message = "Nieznany błąd";

        ErrorDto errorDto = new ErrorDto();
        HttpStatus httpStatus = errorMap.get(exception.getClass());
        if (httpStatus == null) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (exception instanceof BaseException) {
            message = exception.getMessage();
        }

        errorDto.setMessage(message);
        errorDto.setEventId(eventId);
        log.error("Error: {} Processing request: {} raised exception: {}", eventId, req.getRequestURI(), exception.getMessage(), exception);
        return new ResponseEntity<>(errorDto, httpStatus);
    }
}
