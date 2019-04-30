package pl.TomaszKasper.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class Symbol {
    private String symbol_id;
    private String exchange_id;
    private String asset_id_base;
    private String asset_id_quote;

}