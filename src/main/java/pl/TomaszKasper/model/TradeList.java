package pl.TomaszKasper.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TradeList {
    private List<Trade> tradesList;
}
