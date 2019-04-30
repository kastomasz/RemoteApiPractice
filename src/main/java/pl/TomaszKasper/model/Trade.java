package pl.TomaszKasper.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class Trade {
    private String symbol_id;
    private String taker_side;
    private Double price;
    private Double size;
}
