package pl.michalkruk.pz;

import pl.michalkruk.pz.gui.CurrencyRatesAnalyzerFrame;
import org.apache.log4j.Logger;

public class CurrencyRatesAnalyzer {

    public static void main(String[] args){
        Logger.getRootLogger().info("Application start");
        new CurrencyRatesAnalyzerFrame();
    }
}