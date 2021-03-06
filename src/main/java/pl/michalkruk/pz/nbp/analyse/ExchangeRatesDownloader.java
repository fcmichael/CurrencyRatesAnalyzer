package pl.michalkruk.pz.nbp.analyse;

import pl.michalkruk.pz.nbp.exception.RestNBPException;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Optional;
import java.util.stream.Collectors;

class ExchangeRatesDownloader {

    static Optional<String> readUrl(String urlString) {
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        String result;

        try {
            URL url = new URL(urlString);
            inputStreamReader = new InputStreamReader(url.openStream());
            bufferedReader = new BufferedReader(inputStreamReader);

            result = bufferedReader.lines().collect(Collectors.joining());

        } catch (IOException e) {
            Logger.getRootLogger().warn("Exception while URL proceeding: " + urlString);
            throw new RestNBPException();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
            } catch (IOException e) {
                Logger.getRootLogger().warn("Exception while closing reader", e);
            }
        }

        return Optional.ofNullable(result);
    }

}
