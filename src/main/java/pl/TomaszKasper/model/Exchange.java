package pl.TomaszKasper.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class Exchange {
    @JsonProperty(value = "exchange_id")
    private String exchange_id;

    private String name;

    private Long data_trade_count;
}
