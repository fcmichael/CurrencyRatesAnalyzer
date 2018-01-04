package app.gui.search;

import app.gui.search.exception.DateOrderException;
import app.gui.search.exception.DaysLimitExceedException;
import app.gui.search.exception.EmptyCurrencyCodeException;
import app.gui.search.exception.FutureDateException;
import app.i18n.I18nException;
import app.nbp.analyse.CurrencyRateAnalyzer;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SearchAction extends AbstractAction {

    private SearchPanel searchPanel;

    SearchAction(SearchPanel searchPanel) {
        this.searchPanel = searchPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(validateInput()){
                String currencyCode = (String) searchPanel.getCodeComboBox().getSelectedItem();
                SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
                String startDate = dateFormat.format(searchPanel.getStartDate().getDate());
                String endDate = dateFormat.format(searchPanel.getEndDate().getDate());

                CurrencyRateAnalyzer.analyze(currencyCode, startDate, endDate);
            }
        } catch (I18nException ex){
            Logger.getRootLogger().warn(ex.getMessage());
            showValidationError(e, ex.getLocalizedMessage());
        }
    }

    private boolean validateInput(){
        return validateDates() && validateCode();
    }

    private boolean validateCode() {
        JComboBox<String> comboBox = searchPanel.getCodeComboBox();
        if(comboBox.getSelectedItem() == null){
            throw new EmptyCurrencyCodeException();
        }
        return true;
    }

    private boolean validateDates() {
        Date start = searchPanel.getStartDate().getDate();
        Date end = searchPanel.getEndDate().getDate();

        if (!end.after(start)) {
            throw new DateOrderException();
        }

        long diffInMillis = Math.abs(end.getTime() - start.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);

        if(diff > 367){
            throw new DaysLimitExceedException();
        }

        if(end.after(new Date())){
            throw new FutureDateException();
        }

        return true;
    }

    private void showValidationError(ActionEvent e, String message){
        JButton aClass = (JButton) e.getSource();
        JRootPane rootPane = aClass.getRootPane();
        JOptionPane.showMessageDialog(rootPane, message, "", JOptionPane.ERROR_MESSAGE);
    }
}
