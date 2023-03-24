package com.olmez.myamango.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.olmez.myamango.model.CurrencyRate;

@Repository
public interface CurrencyRateRepository extends BaseObjectRepository<CurrencyRate> {

    @Query("{ 'date' : ?0, 'deleted' : false }")
    List<CurrencyRate> findCurrencyRateByDate(LocalDate date);

    default CurrencyRate findByDate(LocalDate date) {
        if (date == null) {
            return null;
        }

        List<CurrencyRate> rates = findCurrencyRateByDate(date);
        if (rates.isEmpty()) {
            return null;
        }

        if (rates.size() > 1) {
            // keep latest one
            for (int i = 1; i < rates.size(); i++) {
                rates.get(i).setDeleted(true);
            }
            saveAll(rates);
        }
        return rates.get(0);
    }

}
