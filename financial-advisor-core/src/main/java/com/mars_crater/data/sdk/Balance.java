package com.mars_crater.data.sdk;

import java.math.BigDecimal;

/**
 * Created by ateixeira on 18-01-2015.
 */
public class Balance {

    private BalanceTypeEnum balanceType;

    private BigDecimal balanceValue;

    private CurrencyEnum currency;

    public Balance(BalanceTypeEnum balanceType, BigDecimal balanceValue, CurrencyEnum currency) {
        this.balanceType = balanceType;
        this.balanceValue = balanceValue;
        this.currency = currency;
    }

    public BalanceTypeEnum getBalanceType() {
        return balanceType;
    }

    public BigDecimal getBalanceValue() {
        return balanceValue;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return balanceType + " " + balanceValue + currency;
    }
}
