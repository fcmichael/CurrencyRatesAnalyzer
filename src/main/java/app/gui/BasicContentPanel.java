package app.gui;

import javax.swing.*;
import java.awt.*;

public class BasicContentPanel extends JPanel{

    protected BasicContentPanel(){
        super(new BorderLayout());
    }

    protected void addContentPanel(JPanel contentPanel){
        add(contentPanel, BorderLayout.CENTER);
    }
}
