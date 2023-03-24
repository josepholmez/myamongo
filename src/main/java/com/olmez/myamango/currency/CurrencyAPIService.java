package com.olmez.myamango.currency;

import java.io.IOException;
import java.time.LocalDate;

import com.olmez.myamango.model.CurrencyRate;

public interface CurrencyAPIService {

    CurrencyRate update() throws IOException, InterruptedException;

    CurrencyRate update(LocalDate date) throws IOException, InterruptedException;

}
