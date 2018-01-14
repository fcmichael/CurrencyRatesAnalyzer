package pl.michalkruk.pz.nbp.analyse;

import pl.michalkruk.pz.db.DbFacade;
import pl.michalkruk.pz.gui.tray.CurrencyRatesAnalyzerTrayIcon;
import pl.michalkruk.pz.nbp.model.Rate;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class AnalyzeThread implements Runnable {

    private final CurrencyRatesAnalyzerTrayIcon trayIcon;

    public AnalyzeThread(CurrencyRatesAnalyzerTrayIcon trayIcon) {
        this.trayIcon = trayIcon;
    }

    @Override
    public void run() {
        List<Rate> actualRates = CurrentRatesProvider.getActualRates();
        List<String> favouriteCurrencyCodes = DbFacade.getInstance().findFavouriteCurrencyCodes();

        List<Rate> favouriteActualRates = actualRates.stream()
                .filter(rate -> favouriteCurrencyCodes.contains(rate.getCode()))
                .sorted(comparing(Rate::getChange, comparing(Math::abs)).reversed())
                .limit(4)
                .collect(Collectors.toList());

        String message = prepareMessage(favouriteActualRates);
        trayIcon.displayMessage(message);
    }

    private String prepareMessage(List<Rate> rates) {
        StringBuilder stringBuilder = new StringBuilder();
        int lineLength = 50;

        for (Rate rate : rates) {
            String messageToShow = rate.getCode() +
                    " : " + BigDecimal.valueOf(rate.getChange()).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();

            stringBuilder.append(messageToShow);
            int emptySeparatorsCount = lineLength - messageToShow.length();
            for (int i = 0; i < emptySeparatorsCount; i++) {
                stringBuilder.append(" ");
            }
        }

        return stringBuilder.toString();
    }
}
