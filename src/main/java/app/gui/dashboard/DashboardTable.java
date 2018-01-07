package app.gui.dashboard;

import app.i18n.MessagesReader;
import app.nbp.analyse.CurrentRatesProvider;
import app.nbp.exception.RestNBPException;
import app.nbp.model.Rate;
import lombok.Getter;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.concurrent.ExecutionException;

@Getter
class DashboardTable extends JTable implements Observer {

    private DashboardTableModel dashboardTableModel;

    DashboardTable() {
        super();
        setTableModel();
        configureTable();
        setTableCellHorizontalAlignment();

        MessagesReader.getInstance().addObserver(this);
    }

    private void configureTable() {
        setModel(dashboardTableModel);
        setFillsViewportHeight(true);
        setAutoCreateRowSorter(true);
        getTableHeader().setReorderingAllowed(false);
        setRowHeight(22);
        setAutoCreateColumnsFromModel(false);
        setSelectionBackground(Color.blue);
    }

    private void setTableModel() {
        this.dashboardTableModel = new DashboardTableModel();
    }

    private void setTableCellHorizontalAlignment() {
        DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < dashboardTableModel.getColumnCount(); i++) {
            getColumnModel().getColumn(i).setCellRenderer(defaultTableCellRenderer);
        }
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        getColumnModel().getColumn(0).setHeaderValue(MessagesReader.getInstance().getMessage("CurrencyCode"));
        getColumnModel().getColumn(1).setHeaderValue(MessagesReader.getInstance().getMessage("CurrencyValue"));
        getColumnModel().getColumn(2).setHeaderValue(MessagesReader.getInstance().getMessage("CurrencyChange"));
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component component = super.prepareRenderer(renderer, row, column);
        Object value = dashboardTableModel.getValueAt(convertRowIndexToModel(row), column);

        if (this.getSelectedRow() == row) {
            component.setBackground(Color.black);
            component.setForeground(Color.white);
        } else {
            if (column == 2) { // rate change
                double val = (double) value;
                if (val > 0) {
                    component.setBackground(Color.green);
                    component.setForeground(Color.black);
                } else if (val < 0) {
                    component.setBackground(Color.red);
                    component.setForeground(Color.black);
                } else {
                    component.setBackground(Color.white);
                    component.setForeground(Color.black);
                }
            } else {
                component.setBackground(Color.white);
                component.setForeground(Color.black);
            }
        }

        return component;
    }

    void updateModel() {
        SwingWorker swingWorker = new SwingWorker<List<Rate>, Object>() {

            @Override
            protected List<Rate> doInBackground() {
                List<Rate> rates = new ArrayList<>();
                try {
                    return CurrentRatesProvider.getActualRates();
                } catch (RestNBPException rest) {
                    Logger.getRootLogger().warn(rest.getMessage());
                    rest.displayMessageDialog(DashboardTable.this);
                }
                return rates;
            }

            @Override
            protected void done() {
                try {
                    List<Rate> rateList = get();
                    dashboardTableModel.setRateList(rateList);
                    dashboardTableModel.fireTableDataChanged();
                } catch (InterruptedException | ExecutionException e) {
                    Logger.getRootLogger().warn("Exception while dashboard data loading", e);
                }
            }
        };

        swingWorker.execute();
    }
}
