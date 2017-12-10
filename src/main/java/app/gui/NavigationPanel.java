package app.gui;

import app.gui.menu.button.MenuButton;
import app.gui.panel.DashboardPanel;
import app.gui.panel.FavouritePanel;
import app.gui.panel.SearchPanel;
import app.gui.panel.SettingsPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

class NavigationPanel extends JPanel {

    private JPanel cardPanel;
    private final List<String> PANEL_NAMES = Arrays.asList("Dashboard", "Favourite", "Search", "Settings");

    NavigationPanel() {
        super(new BorderLayout());
        cardPanel = new JPanel(new CardLayout());
        cardPanel.add(new DashboardPanel(), PANEL_NAMES.get(0));
        cardPanel.add(new FavouritePanel(), PANEL_NAMES.get(1));
        cardPanel.add(new SearchPanel(), PANEL_NAMES.get(2));
        cardPanel.add(new SettingsPanel(), PANEL_NAMES.get(3));
        add(cardPanel, BorderLayout.CENTER);

        JPanel menuPanel = getMenuPanel();
        add(menuPanel, BorderLayout.PAGE_START);
    }

    private JPanel getMenuPanel(){
        final JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(0,PANEL_NAMES.size()));
        PANEL_NAMES.forEach(name -> menuPanel.add(new MenuButton(name, this.cardPanel)));
        return menuPanel;
    }
}
