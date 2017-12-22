package app.gui.dashboard;

import app.nbp.analyse.CurrentRatesProvider;
import app.nbp.model.Rate;
import lombok.Getter;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Getter
class DashboardTable extends JTable {

	private DashboardTableModel dashboardTableModel;

	DashboardTable() {
		super();
		this.dashboardTableModel = new DashboardTableModel();
		setModel(dashboardTableModel);
		setFillsViewportHeight(true);
		setAutoCreateRowSorter(true);
		getTableHeader().setReorderingAllowed(false);
		setRowHeight(22);
		DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
		defaultTableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < dashboardTableModel.getColumnCount(); i++) {
			getColumnModel().getColumn(i).setCellRenderer(defaultTableCellRenderer);
		}

	}

	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		Component component = super.prepareRenderer(renderer, row, column);
		Object value = dashboardTableModel.getValueAt(convertRowIndexToModel(row), column);

		if (column == 2) {
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
		SwingWorker swingWorker = new SwingWorker() {
			@Override
			protected Object doInBackground() throws Exception {
				System.out.println("DOING IN BACKGROUND");
				List<Rate> rateList = null;
				try {
					rateList = CurrentRatesProvider.getActualRates();
					System.out.println("OK");
				} catch (Exception e) {
					System.out.println("ERROR");
					e.printStackTrace();
				}
				return rateList;
			}

			@Override
			protected void done() {
				try {
					List<Rate> rateList = (List<Rate>) get();
					if(rateList != null){
						dashboardTableModel.setRateList(rateList);
						dashboardTableModel.fireTableDataChanged();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		};

		swingWorker.execute();
	}
}
