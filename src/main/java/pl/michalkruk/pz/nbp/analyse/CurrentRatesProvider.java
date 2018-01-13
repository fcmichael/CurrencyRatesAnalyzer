package pl.michalkruk.pz.nbp.analyse;

import pl.michalkruk.pz.nbp.model.Rate;
import pl.michalkruk.pz.nbp.model.RateDTO;
import com.google.gson.Gson;

import java.util.List;

public class CurrentRatesProvider {

    public static List<Rate> getActualRates() {
        String json = ExchangeRatesDownloader
                .readUrl("http://api.nbp.pl/api/exchangerates/tables/a/last/2?format=json")
                .orElse("");
        Gson gson = new Gson();

        List<Rate> yesterdayRates = gson.fromJson(json, RateDTO[].class)[0].getRates();
        List<Rate> todayRates = gson.fromJson(json, RateDTO[].class)[1].getRates();
        int ratesCount = todayRates.size();

        for (int i = 0; i < ratesCount; i++) {
            Rate today = todayRates.get(i);
            Rate yesterday = yesterdayRates.get(i);
            double change = today.getMid() - yesterday.getMid();
            today.setChange(change);
        }

        return todayRates;
    }
}
