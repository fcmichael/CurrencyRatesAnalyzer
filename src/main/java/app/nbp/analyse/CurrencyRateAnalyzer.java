package app.nbp.analyse;

import app.nbp.model.Rate;
import app.nbp.model.RateDTO;
import com.google.gson.Gson;

import java.util.List;

public class CurrencyRateAnalyzer {

    public static List<Rate> analyze(String currencyCode, String startDate, String endDate) {
        String url = "http://api.nbp.pl/api/exchangerates/rates/a/" +
                currencyCode + "/" +
                startDate + "/" +
                endDate + "?format=json";

        String json = ExchangeRatesDownloader
                .readUrl(url)
                .orElse("");

        Gson gson = new Gson();

        RateDTO tmpDTO = gson.fromJson(json, RateDTO.class);
        return tmpDTO.getRates();
    }

}