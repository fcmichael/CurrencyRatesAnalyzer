package app.nbp.analyse;

import app.nbp.model.RateDTO;
import app.nbp.service.ExchangeRatesDownloader;
import com.google.gson.Gson;

public class CurrencyRateAnalyzer {

    public static void analyze(String currencyCode, String startDate, String endDate) {
        String url = "http://api.nbp.pl/api/exchangerates/rates/a/" +
                currencyCode + "/" +
                startDate + "/" +
                endDate + "?format=json";

        System.out.println(url);

        String json = ExchangeRatesDownloader
                .readUrl(url)
                .orElseThrow(RuntimeException::new);

        Gson gson = new Gson();

        RateDTO tmpDTO = gson.fromJson(json, RateDTO.class);
        tmpDTO.getRates().forEach(System.out::println);

    }

}