package pl.TomaszKasper.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class SymbolCounterDto {

    private String symbol_id;
    private Long counter;

}
