package app.gui.dashboard;

import app.i18n.MessagesReader;
import app.nbp.model.Rate;
import lombok.Getter;

import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
class DashboardTableModel extends AbstractTableModel {

    private List<Rate> rateList = new ArrayList<>();
    private String[] columnNames = {
            MessagesReader.getInstance().getMessage("CurrencyCode"),
            MessagesReader.getInstance().getMessage("CurrencyValue"),
            MessagesReader.getInstance().getMessage("CurrencyChange"),
            MessagesReader.getInstance().getMessage("Favourite")
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
            rateList.get(row).setFavourite((Boolean) value);
            fireTableCellUpdated(row, col);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 3;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
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
