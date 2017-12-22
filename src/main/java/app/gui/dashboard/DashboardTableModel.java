package app.gui.dashboard;

import app.i18n.MessagesReader;
import app.nbp.model.Rate;
import lombok.Setter;

import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

@Setter
class DashboardTableModel extends AbstractTableModel implements Observer{

	private List<Rate> rateList = new ArrayList<>();
	private String[] columnNames = {"Kod waluty", "Wartość", "Zmiana"};

	DashboardTableModel() {
		MessagesReader.getInstance().addObserver(this);
	}

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
				return BigDecimal.valueOf(rate.getMid()).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
			case 2:
				return BigDecimal.valueOf(rate.getChange()).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
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

	@Override
	public void update(Observable o, Object arg) {
		// TODO:
		columnNames[0] = "AAA";
		columnNames[1] = "BBB";
		columnNames[2] = "CCC";
		fireTableDataChanged();
	}
}
