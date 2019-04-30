package pl.TomaszKasper.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorDto {
    private String eventId;
    private String message;
}
