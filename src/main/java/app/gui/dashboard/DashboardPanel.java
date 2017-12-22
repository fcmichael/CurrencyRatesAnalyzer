package app.gui.dashboard;


import app.gui.BasicContentPanel;
import app.i18n.MessagesReader;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class DashboardPanel extends BasicContentPanel implements Observer{

	private final JPanel contentPanel;
	private DashboardTable table;
	private String messageKey = "DashboardTableHeader";
	private JLabel jLabel = new JLabel(MessagesReader.getInstance().getMessage(messageKey));

	public DashboardPanel() {
		super("Dashboard");
		this.contentPanel = new JPanel(new BorderLayout());
		this.table = new DashboardTable();

		jLabel.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel.setPreferredSize(new Dimension(100, 50));
		contentPanel.add(jLabel, BorderLayout.NORTH);
		contentPanel.add(new JScrollPane(table), BorderLayout.CENTER);
		MessagesReader.getInstance().addObserver(this);
		updateData();
		addContentPanel(contentPanel);
	}

	@Override
	public void updateData() {
		table.updateModel();
	}

	@Override
	public void update(Observable o, Object arg) {
		jLabel.setText(MessagesReader.getInstance().getMessage(messageKey));
	}
}
