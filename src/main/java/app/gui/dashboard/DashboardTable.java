package app.gui.dashboard;

import app.i18n.MessagesReader;
import app.nbp.analyse.CurrentRatesProvider;
import app.nbp.model.Rate;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.List;
import java.util.Observer;
import java.util.concurrent.ExecutionException;

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
        getColumnModel().getColumn(0).setHeaderValue(MessagesReader.getInstance().getMessage("DashboardTableCurrencyCode"));
        getColumnModel().getColumn(1).setHeaderValue(MessagesReader.getInstance().getMessage("DashboardTableCurrencyValue"));
        getColumnModel().getColumn(2).setHeaderValue(MessagesReader.getInstance().getMessage("DashboardTableCurrencyChange"));
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component component = super.prepareRenderer(renderer, row, column);
        Object value = dashboardTableModel.getValueAt(convertRowIndexToModel(row), column);

        if (column == 2) { // rate change
            double val = (double) value;
            if (val > 0) {
                component.setBackground(Color.green);
            } else if (val < 0) {
                component.setBackground(Color.red);
            } else {
                component.setBackground(Color.white);
            }
        } else {
            component.setBackground(Color.white);
        }

        return component;
    }

    void updateModel() {
        SwingWorker swingWorker = new SwingWorker<List<Rate>, Object>() {

            @Override
            protected List<Rate> doInBackground() {
                return CurrentRatesProvider.getActualRates();
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
