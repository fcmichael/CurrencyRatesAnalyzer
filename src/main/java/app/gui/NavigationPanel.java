package app.gui;

import app.gui.dashboard.DashboardPanel;
import app.gui.menu.MenuButton;
import app.gui.search.SearchPanel;
import app.gui.settings.SettingsPanel;
import app.i18n.MessagesReader;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class NavigationPanel extends JPanel {

	private final JPanel menuPanel;
	private final JPanel cardPanel;
	private List<MenuButton> menuButtonList;
	private final Map<String, BasicContentPanel> PANELS = new LinkedHashMap<String, BasicContentPanel>() {{
		put("Dashboard", new DashboardPanel());
		put("Search", new SearchPanel());
		put("Settings", new SettingsPanel());
	}};

	NavigationPanel() {
		super(new BorderLayout());
		this.cardPanel = setCardPanel();
		this.menuPanel = setMenuPanel();

		add(cardPanel, BorderLayout.CENTER);
		add(menuPanel, BorderLayout.PAGE_START);
	}

	private JPanel setMenuPanel() {
		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new GridLayout(0, PANELS.size()));
		this.menuButtonList = PANELS.entrySet().stream()
				.map(entry -> new MenuButton(MessagesReader.getInstance().getMessage(entry.getKey()), this.cardPanel, entry.getValue(), entry.getValue().getMnemonic()))
				.collect(Collectors.toList());
		this.menuButtonList.forEach(menuPanel::add);
		return menuPanel;
	}

	private JPanel setCardPanel() {
		JPanel cardPanel = new JPanel(new CardLayout());
		PANELS.forEach((s, basicContentPanel) -> cardPanel.add(basicContentPanel, s));
		return cardPanel;
	}
}
