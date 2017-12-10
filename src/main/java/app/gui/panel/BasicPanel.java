package app.gui.panel;

import javax.swing.*;
import java.awt.*;

class BasicPanel extends JPanel{

    BasicPanel(){
        super(new BorderLayout());
        setMenuPanel();
    }

    private void setMenuPanel(){
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(0,4));
        menuPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
        menuPanel.add(new JButton("Dashboard"));
        menuPanel.add(new JButton("Favourite"));
        menuPanel.add(new JButton("Search"));
        menuPanel.add(new JButton("Tools"));
        add(menuPanel, BorderLayout.PAGE_START);
    }

    void setContentPanel(JPanel contentPanel){
        add(contentPanel, BorderLayout.CENTER);
    }
}
