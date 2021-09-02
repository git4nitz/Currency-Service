package com.company.project.services.ccyservice;

import com.instansys.datapex.model.CurrencyExchangeRateModel;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
public class CurrencyServiceImpl {

    // map keeps rate for the given currency pair, for example USD to EUR exchange rate.
    HashMap<Pair, Integer>map = new HashMap<>();

    // assuming every save is the latest exchange rate for the given pair
    // so if a pair exist, the new save call will overwrite the exchange rate
    // hence we only save the latest rate, since the get has to return the latest rate
    public void saveExchangeRate(CurrencyExchangeRateModel model){
        map.put(new Pair(model.getFromCurrency(), model.getToCurrency()), model.getExchangeRate());
    }

    // get the latest rate.
    public int getExchangeRate(String from, String to){
        return map.getOrDefault(new Pair(from, to), -1);
    }

    // lets make a Pair that keeps currencyFrom( say USD) and currencyTo(EUR)
    public class Pair{
        private final String currencyFrom;
        private final  String currencyTo;

        public Pair(String x, String y){
            this.currencyFrom = x;
            this.currencyTo = y;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;
            Pair key = (Pair) o;
            return currencyFrom.equals(key.currencyFrom) && currencyTo.equals(key.currencyTo);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.currencyFrom, this.currencyTo);
        }
    }
}
