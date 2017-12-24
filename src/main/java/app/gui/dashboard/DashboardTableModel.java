package app.gui.dashboard;

import app.i18n.MessagesReader;
import app.nbp.model.Rate;
import lombok.Getter;

import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
class DashboardTableModel extends AbstractTableModel{

    private List<Rate> rateList = new ArrayList<>();
    private String[] columnNames = {
            MessagesReader.getInstance().getMessage("DashboardTableCurrencyCode"),
            MessagesReader.getInstance().getMessage("DashboardTableCurrencyValue"),
            MessagesReader.getInstance().getMessage("DashboardTableCurrencyChange")
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
            default:
                return null;
        }
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
            default:
                return null;
        }
    }

    void setRateList(List<Rate> rateList) {
        this.rateList = rateList;
    }
}
