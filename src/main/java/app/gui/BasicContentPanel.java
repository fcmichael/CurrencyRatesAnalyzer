package app.gui;

import javax.swing.*;
import java.awt.*;

public abstract class BasicContentPanel extends JPanel implements DataUpdater{

    protected BasicContentPanel(String name){
        super(new BorderLayout());
        setName(name);
    }

    protected void addContentPanel(JPanel contentPanel){
        add(contentPanel, BorderLayout.CENTER);
    }
}
