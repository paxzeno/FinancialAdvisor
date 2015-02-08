package com.mars_crater.data.sdk.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ateixeira on 18-01-2015.
 */
public class NBExtract implements BankExtract {

    private List<Transaction> extract;

    public NBExtract() {
        this.extract = new ArrayList<Transaction>();
    }

    public void put(Transaction besTransaction) {
        this.extract.add(besTransaction);
    }

    public List<Transaction> getExtract() {
        return this.extract;
    }
}
