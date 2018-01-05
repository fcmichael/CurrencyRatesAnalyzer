package app.gui.dashboard;

import app.gui.BasicContentPanel;
import app.gui.settings.plaf.PLAFConfiguration;
import app.i18n.MessagesReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

public class DashboardPanel extends BasicContentPanel implements Observer {

	private DashboardTable table;
	private JLabel tableLabel;
	private final String tableLabelMessageKey = "DashboardTableHeader";

	public DashboardPanel() {
		super("Dashboard", KeyEvent.VK_G);
		setTableLabel();
		setTable();
		setContentPanel();

		MessagesReader.getInstance().addObserver(this);
		PLAFConfiguration.getInstance().addObserver(this);
	}

	private void setTableLabel() {
		this.tableLabel = new JLabel(MessagesReader.getInstance().getMessage(tableLabelMessageKey));
		this.tableLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.tableLabel.setPreferredSize(new Dimension(100, 50));
	}

	private void setTable() {
		this.table = new DashboardTable();
		updateData();
	}

	private void setContentPanel() {
		JPanel contentPanel = new JPanel(new BorderLayout());
		contentPanel.add(tableLabel, BorderLayout.NORTH);
		contentPanel.add(new JScrollPane(table), BorderLayout.CENTER);
		addContentPanel(contentPanel);
	}

	@Override
	public void updateData() {
		table.updateModel();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof PLAFConfiguration) {
			this.table.setShowHorizontalLines(true);
			this.table.setShowVerticalLines(true);
		} else if (o instanceof MessagesReader) {
			tableLabel.setText(MessagesReader.getInstance().getMessage(tableLabelMessageKey));
		}

	}
}
