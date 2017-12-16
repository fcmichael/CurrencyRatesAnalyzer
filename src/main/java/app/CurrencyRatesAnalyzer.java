package app;

import app.db.DbFacade;
import app.gui.CurrencyRatesAnalyzerFrame;
import org.apache.log4j.Logger;

public class CurrencyRatesAnalyzer {

    public static void main(String[] args) {
        DbFacade.getInstance();

        Logger.getRootLogger().info("Application start");
        new CurrencyRatesAnalyzerFrame();
        Logger.getRootLogger().info("Application end");
    }
}
