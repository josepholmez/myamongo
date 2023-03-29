package com.olmez.myamango.currency;

import java.time.LocalDate;

import com.olmez.myamango.model.enums.CurrencyCode;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CurrencyWrapper {
    private LocalDate date;
    private Double amount;
    private CurrencyCode from;
    private CurrencyCode to;

    public CurrencyWrapper(LocalDate date, Double amount, CurrencyCode from, CurrencyCode to) {
        this.date = date;
        this.amount = amount;
        this.from = from;
        this.to = to;
    }
}
