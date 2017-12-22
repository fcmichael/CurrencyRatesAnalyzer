package app.gui.search;


import app.gui.BasicContentPanel;

import javax.swing.*;
import java.awt.*;

public class SearchPanel extends BasicContentPanel {

	private final JPanel contentPanel;

	public SearchPanel() {
		super("Search");
		this.contentPanel = setContentPanel();
		addContentPanel(contentPanel);
	}

	private JPanel setContentPanel() {
		JPanel contentPanel = new JPanel();
		Label l = new Label("Search Panel");
		contentPanel.add(l);
		return contentPanel;
	}

	@Override
	public void updateData() {
		System.out.println("SEARCH update");
	}

}
