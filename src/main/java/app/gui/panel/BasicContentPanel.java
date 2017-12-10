package app.gui.panel;

import javax.swing.*;
import java.awt.*;

class BasicContentPanel extends JPanel{

    BasicContentPanel(){
        super(new BorderLayout());
    }

    void setContentPanel(JPanel contentPanel){
        add(contentPanel, BorderLayout.CENTER);
    }
}
