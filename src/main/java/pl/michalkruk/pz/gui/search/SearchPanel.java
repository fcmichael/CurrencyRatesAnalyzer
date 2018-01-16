package pl.michalkruk.pz.gui.search;

import pl.michalkruk.pz.gui.BasicContentPanel;
import pl.michalkruk.pz.gui.settings.plaf.PLAFConfiguration;
import pl.michalkruk.pz.i18n.MessagesReader;
import pl.michalkruk.pz.nbp.analyse.CurrencyCodesProvider;
import pl.michalkruk.pz.nbp.exception.RestNBPException;
import lombok.Getter;
import org.apache.log4j.Logger;
import org.jdesktop.swingx.JXDatePicker;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Getter
public class SearchPanel extends BasicContentPanel implements Observer {

    private final String CHART_X_LABEL_MESSAGE_KEY = "SearchChartXLabel";
    private final String CHART_Y_LABEL_MESSAGE_KEY = "SearchChartYLabel";
    private final String CODE_LABEL_MESSAGE_KEY = "CurrencyCode";
    private final String START_DATE_LABEL_MESSAGE_KEY = "StartDate";
    private final String END_DATE_LABEL_MESSAGE_KEY = "EndDate";
    private final String SEARCH_BUTTON_LABEL_MESSAGE_KEY = "Search";
    private final JPanel contentPanel;
    private JPanel formPanel;
    private ChartPanel chartPanel;
    private JLabel codeLabel;
    private JComboBox<String> codeComboBox;
    private JLabel startDateLabel;
    private JLabel endDateLabel;
    private JXDatePicker startDate;
    private JXDatePicker endDate;
    private JButton searchButton;
    private MessagesReader messagesReader;

    public SearchPanel() {
        super("Search", KeyEvent.VK_S);
        contentPanel = new JPanel(new BorderLayout());
        messagesReader = MessagesReader.getInstance();
        chartPanel = new ChartPanel(SearchChartPanel.createChart(Collections.emptyList(), ""));
        setFormPanel();

        contentPanel.add(formPanel, BorderLayout.NORTH);
        contentPanel.add(chartPanel, BorderLayout.CENTER);
        addContentPanel(contentPanel);

        MessagesReader.getInstance().addObserver(this);
        PLAFConfiguration.getInstance().addObserver(this);
    }

    private void setFormPanel() {
        formPanel = new JPanel(new GridBagLayout());
        setCodeInput();
        setStartDateInput();
        setEndDateInput();
        setSearchButton();
    }

    private void setCodeInput() {
        JPanel jPanel = new JPanel();
        codeLabel = new JLabel(messagesReader.getMessage(CODE_LABEL_MESSAGE_KEY));
        codeComboBox = new JComboBox<>(new String[]{});

        jPanel.add(codeLabel);
        jPanel.add(codeComboBox);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.insets = new Insets(20, 0, 0, 0);

        formPanel.add(jPanel, constraints);
    }

    private void setStartDateInput() {
        JPanel jPanel = new JPanel();
        startDateLabel = new JLabel(messagesReader.getMessage(START_DATE_LABEL_MESSAGE_KEY));
        jPanel.add(startDateLabel);

        startDate = new JXDatePicker();
        configureDatePicker(startDate);
        startDate.setDate(new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000));
        jPanel.add(startDate);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 0;
        constraints.gridx = 1;
        constraints.ipadx = 20;
        constraints.insets = new Insets(20, 0, 0, 0);

        formPanel.add(jPanel, constraints);
    }

    private void setEndDateInput() {
        JPanel jPanel = new JPanel();
        endDateLabel = new JLabel(messagesReader.getMessage(END_DATE_LABEL_MESSAGE_KEY));
        jPanel.add(endDateLabel);

        endDate = new JXDatePicker();
        configureDatePicker(endDate);
        endDate.setDate(new Date());
        jPanel.add(endDate);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 0;
        constraints.gridx = 2;
        constraints.ipadx = 20;
        constraints.insets = new Insets(20, 0, 0, 0);

        formPanel.add(jPanel, constraints);
    }

    private void setSearchButton() {
        searchButton = new JButton(messagesReader.getMessage(SEARCH_BUTTON_LABEL_MESSAGE_KEY));
        searchButton.addActionListener(new SearchAction(this));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 1;
        constraints.gridx = 1;
        constraints.insets = new Insets(20, 0, 20, 0);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;

        formPanel.add(searchButton, constraints);
    }

    private void configureDatePicker(JXDatePicker datePicker) {
        datePicker.getEditor().setEditable(false);
        datePicker.setLocale(messagesReader.getCurrentLanguage().getLocale());
        JPanel linkPanel = new JPanel();
        linkPanel.setVisible(false);
        datePicker.setLinkPanel(linkPanel);
    }

    @Override
    public void updateData() {
        SwingWorker swingWorker = new SwingWorker<List<String>, Object>() {

            @Override
            protected List<String> doInBackground() {
                List<String> codes = new ArrayList<>();
                try {
                    codes = CurrencyCodesProvider.getCurrencyCodes();
                } catch(RestNBPException rest){
                    Logger.getRootLogger().warn(rest.getMessage());
                    rest.displayMessageDialog(SearchPanel.this);
                }
                return codes;
            }

            @Override
            protected void done() {
                try {
                    List<String> rateList = get();
                    String[] strings = rateList.toArray(new String[0]);
                    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(strings);
                    codeComboBox.setModel(model);
                } catch (InterruptedException | ExecutionException e) {
                    Logger.getRootLogger().warn("Exception while currency codes loading", e);
                }
            }
        };

        swingWorker.execute();
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof PLAFConfiguration){
            JPanel linkPanel = new JPanel();
            linkPanel.setVisible(false);
            startDate.setLinkPanel(linkPanel);
            endDate.setLinkPanel(linkPanel);
        } else if(o instanceof MessagesReader){
            codeLabel.setText(messagesReader.getMessage(CODE_LABEL_MESSAGE_KEY));
            startDateLabel.setText(messagesReader.getMessage(START_DATE_LABEL_MESSAGE_KEY));
            endDateLabel.setText(messagesReader.getMessage(END_DATE_LABEL_MESSAGE_KEY));
            searchButton.setText(messagesReader.getMessage(SEARCH_BUTTON_LABEL_MESSAGE_KEY));
            Locale locale = messagesReader.getCurrentLanguage().getLocale();
            startDate.setLocale(locale);
            endDate.setLocale(locale);
            chartPanel.getChart().getXYPlot().getDomainAxis().setLabel(messagesReader.getMessage(CHART_X_LABEL_MESSAGE_KEY));
            chartPanel.getChart().getXYPlot().getRangeAxis().setLabel(messagesReader.getMessage(CHART_Y_LABEL_MESSAGE_KEY));
        }
    }
}
