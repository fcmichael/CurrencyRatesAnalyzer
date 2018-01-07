package app.gui.dashboard;

import app.db.DbFacade;
import app.i18n.MessagesReader;
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

        MessagesReader.getInstance().addObserver(this);
    }

    private void configureTable() {
        setModel(dashboardTableModel);
        setFillsViewportHeight(true);
        setAutoCreateRowSorter(true);
        getTableHeader().setReorderingAllowed(false);
        setRowHeight(22);
        setAutoCreateColumnsFromModel(false);
        setSelectionBackground(Color.black);
        setSelectionForeground(Color.white);
    }

    private void setTableModel() {
        this.dashboardTableModel = new DashboardTableModel();
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        getColumnModel().getColumn(0).setHeaderValue(MessagesReader.getInstance().getMessage("CurrencyCode"));
        getColumnModel().getColumn(1).setHeaderValue(MessagesReader.getInstance().getMessage("CurrencyValue"));
        getColumnModel().getColumn(2).setHeaderValue(MessagesReader.getInstance().getMessage("CurrencyChange"));
        getColumnModel().getColumn(3).setHeaderValue(MessagesReader.getInstance().getMessage("Favourite"));
    }

    @Override
    public TableCellRenderer getCellRenderer(int row, int column) {
        if (column == 3) {
            return super.getCellRenderer(row, column);
        } else {

            DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
            cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            if (column == 2) {
                double val = (double) getValueAt(row, column);
                if (val > 0) {
                    cellRenderer.setBackground(Color.green);
                    cellRenderer.setForeground(Color.black);
                } else if (val < 0) {
                    cellRenderer.setBackground(Color.red);
                    cellRenderer.setForeground(Color.black);
                } else {
                    cellRenderer.setBackground(Color.white);
                    cellRenderer.setForeground(Color.black);
                }
            }

            return cellRenderer;
        }
    }

    void updateModel() {
        SwingWorker swingWorker = new SwingWorker<List<Rate>, Object>() {

            @Override
            protected List<Rate> doInBackground() {
                List<Rate> rates = new ArrayList<>();
                try {
                    return DbFacade.getInstance().findAndUpdate();
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
