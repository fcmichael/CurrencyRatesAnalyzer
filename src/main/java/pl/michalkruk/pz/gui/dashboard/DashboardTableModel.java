package pl.michalkruk.pz.gui.dashboard;

import org.apache.log4j.Logger;
import pl.michalkruk.pz.db.DbFacade;
import pl.michalkruk.pz.i18n.MessagesReader;
import pl.michalkruk.pz.nbp.model.Rate;
import lombok.Getter;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Getter
class DashboardTableModel extends AbstractTableModel {

    private List<Rate> rateList = new ArrayList<>();
    private String[] columnNames = {
            "CurrencyCode",
            "CurrencyValue",
            "CurrencyChange",
            "Favourite"
    };

    @Override
    public int getRowCount() {
        return rateList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Rate rate = rateList.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return rate.getCode();
            case 1:
                return BigDecimal.valueOf(rate.getMid()).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
            case 2:
                return BigDecimal.valueOf(rate.getChange()).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
            case 3: {
                return rate.isFavourite();
            }
            default:
                return null;
        }
    }

    public void setValueAt(Object value, int row, int col) {
        if (col == 3) {
            final String code = rateList.get(row).getCode();
            final Boolean favourite = (Boolean) value;

            Logger.getRootLogger().info(code + " favourite property changed to: " + favourite);
            SwingWorker swingWorker = new SwingWorker<List<Rate>, Object>() {

                @Override
                protected List<Rate> doInBackground() {
                    DbFacade dbFacade = DbFacade.getInstance();
                    dbFacade.updateFavouriteByCode(code, favourite);
                    return dbFacade.findAll();
                }

                @Override
                protected void done() {
                    try {
                        List<Rate> rateList = get();
                        setRateList(rateList);
                        fireTableDataChanged();
                    } catch (InterruptedException | ExecutionException e) {
                        Logger.getRootLogger().warn("Exception while updating favourite", e);
                    }
                }
            };

            swingWorker.execute();
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 3;
    }

    @Override
    public String getColumnName(int column) {
        return MessagesReader.getInstance().getMessage(columnNames[column]);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return Double.class;
            case 2:
                return Double.class;
            case 3:
                return Boolean.class;
            default:
                return null;
        }
    }

    void setRateList(List<Rate> rateList) {
        this.rateList = rateList;
    }
}
