package pl.michalkruk.pz.gui.dashboard;

import pl.michalkruk.pz.gui.BasicContentPanel;
import pl.michalkruk.pz.gui.settings.plaf.PLAFConfiguration;
import pl.michalkruk.pz.i18n.MessagesReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

public class DashboardPanel extends BasicContentPanel implements Observer {

	private DashboardTable table;

	public DashboardPanel() {
		super("Dashboard", KeyEvent.VK_G);
		setTable();
		setContentPanel();

		MessagesReader.getInstance().addObserver(this);
		PLAFConfiguration.getInstance().addObserver(this);
	}

	private void setTable() {
		this.table = new DashboardTable();
		updateData();
	}

	private void setContentPanel() {
		JPanel contentPanel = new JPanel(new BorderLayout());
		contentPanel.add(new DashboardLabelPanel(), BorderLayout.NORTH);
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
		}
	}
}
