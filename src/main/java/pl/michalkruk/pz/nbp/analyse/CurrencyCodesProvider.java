package pl.michalkruk.pz.nbp.analyse;

import pl.michalkruk.pz.nbp.model.Rate;
import pl.michalkruk.pz.nbp.model.RateDTO;
import com.google.gson.Gson;

import java.util.List;
import java.util.stream.Collectors;

public class CurrencyCodesProvider {

    public static List<String> getCurrencyCodes(){
        String json = ExchangeRatesDownloader
                .readUrl("http://api.nbp.pl/api/exchangerates/tables/a?format=json")
                .orElse("");
        Gson gson = new Gson();
        List<Rate> rates = gson.fromJson(json, RateDTO[].class)[0].getRates();
        return rates.stream().map(Rate::getCode).sorted().collect(Collectors.toList());
    }

}
