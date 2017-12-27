package app.gui.search;

import app.gui.BasicContentPanel;
import app.i18n.MessagesReader;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.Locale;

public class SearchPanel extends BasicContentPanel {

    private final String CODE_LABEL_MESSAGE_KEY = "CurrencyCode";
    private final String START_DATE_LABEL_MESSAGE_KEY = "StartDate";
    private final String END_DATE_LABEL_MESSAGE_KEY = "EndDate";
    private final String SEARCH_BUTTON_LABEL_MESSAGE_KEY = "Search";
    private final JPanel contentPanel;
    private JPanel formPanel;
    //    private JPanel chartPanel;
    private JLabel codeLabel;
    private JLabel startDateLabel;
    private JLabel endDateLabel;
    private JXDatePicker startDate;
    private JXDatePicker endDate;
    private JButton searchButton;
    private MessagesReader messagesReader;

    public SearchPanel() {
        super("Search");
        contentPanel = new JPanel(new BorderLayout());
        messagesReader = MessagesReader.getInstance();
        setFormPanel();

        contentPanel.add(formPanel, BorderLayout.NORTH);
//		contentPanel.add(chartPanel, BorderLayout.CENTER);
        addContentPanel(contentPanel);
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
        String[] codes = {"EUR", "USD"};
        JComboBox<String> codeComboBox = new JComboBox<>(codes);

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
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 1;
        constraints.gridx = 1;
        constraints.insets = new Insets(20, 0, 0, 0);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;

        formPanel.add(searchButton, constraints);
    }

    private void configureDatePicker(JXDatePicker datePicker) {
        datePicker.getEditor().setEditable(false);
        datePicker.setLocale(messagesReader.getCurrentLanguage().getLocale());
        datePicker.setDate(new Date());
        JPanel linkPanel = new JPanel();
        linkPanel.setVisible(false);
        datePicker.setLinkPanel(linkPanel);
    }

    @Override
    public void updateData() {
        codeLabel.setText(messagesReader.getMessage(CODE_LABEL_MESSAGE_KEY));
        startDateLabel.setText(messagesReader.getMessage(START_DATE_LABEL_MESSAGE_KEY));
        endDateLabel.setText(messagesReader.getMessage(END_DATE_LABEL_MESSAGE_KEY));
        searchButton.setText(messagesReader.getMessage(SEARCH_BUTTON_LABEL_MESSAGE_KEY));
        Locale locale = messagesReader.getCurrentLanguage().getLocale();
        startDate.setLocale(locale);
        endDate.setLocale(locale);
    }

}
