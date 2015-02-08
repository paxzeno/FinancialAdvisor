package com.mars_crater.data.sdk.entities;

/**
 * Created by ateixeira on 18-01-2015.
 */
public enum CurrencyEnum {
    EUR("€"), USD("$");

    private String symbol;

    CurrencyEnum(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public static CurrencyEnum findBySymbol(String value) {
        for (CurrencyEnum currency : values()) {
            if (currency.getSymbol().equals(value)) {
                return currency;
            }
        }
        return null;
    }
}
