package app.gui;

import app.gui.menu.MenuButton;
import app.gui.dashboard.DashboardPanel;
import app.gui.favourite.FavouritePanel;
import app.gui.search.SearchPanel;
import app.gui.settings.SettingsPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

class NavigationPanel extends JPanel {

    private JPanel menuPanel;
    private JPanel contentPanel;
    private final List<String> PANEL_NAMES_IN_CARD_LAYOUT = Arrays.asList("Dashboard", "Favourite", "Search", "Settings");

    NavigationPanel() {
        super(new BorderLayout());
        this.contentPanel = setContentPanel();
        this.menuPanel = setMenuPanel();

        add(contentPanel, BorderLayout.CENTER);
        add(menuPanel, BorderLayout.PAGE_START);
    }

    private JPanel setMenuPanel(){
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(0,PANEL_NAMES_IN_CARD_LAYOUT.size()));
        PANEL_NAMES_IN_CARD_LAYOUT.forEach(name -> menuPanel.add(new MenuButton(name, this.contentPanel)));
        return menuPanel;
    }

    private JPanel setContentPanel(){
        JPanel contentPanel = new JPanel(new CardLayout());
        contentPanel.add(new DashboardPanel(), PANEL_NAMES_IN_CARD_LAYOUT.get(0));
        contentPanel.add(new FavouritePanel(), PANEL_NAMES_IN_CARD_LAYOUT.get(1));
        contentPanel.add(new SearchPanel(), PANEL_NAMES_IN_CARD_LAYOUT.get(2));
        contentPanel.add(new SettingsPanel(), PANEL_NAMES_IN_CARD_LAYOUT.get(3));
        return contentPanel;
    }
}
