package pl.michalkruk.pz.gui;

import javax.swing.*;
import java.awt.*;

public abstract class BasicContentPanel extends JPanel implements DataUpdater{

    private final Integer mnemonic;

    protected BasicContentPanel(String name, Integer mnemonic){
        super(new BorderLayout());
        setName(name);
        this.mnemonic = mnemonic;
    }

    protected void addContentPanel(JPanel contentPanel){
        add(contentPanel, BorderLayout.CENTER);
    }

    Integer getMnemonic(){
        return mnemonic;
    }
}
