package app.gui.settings.plaf;

import com.sun.java.swing.plaf.motif.MotifLookAndFeel;
import lombok.Getter;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.util.Map;
import java.util.Observable;
import java.util.TreeMap;

@Getter
public class PLAFConfiguration extends Observable{

	private static PLAFConfiguration instance = new PLAFConfiguration();
	private final Map<String, LookAndFeel> LOOK_AND_FEEL_LIST;
	private LookAndFeel currentLookAndFeel;

	private PLAFConfiguration() {
		this.LOOK_AND_FEEL_LIST = new TreeMap<String, LookAndFeel>() {{
			put("Metal", new MetalLookAndFeel());
			put("Motif", new MotifLookAndFeel());
			put("Nimbus", new NimbusLookAndFeel());
		}};
		this.currentLookAndFeel = LOOK_AND_FEEL_LIST.get("Metal");
	}

	public static PLAFConfiguration getInstance() {
		return instance;
	}

	void changePLAF(LookAndFeel lookAndFeel, JRootPane rootPane) {
		try {
			UIManager.setLookAndFeel(lookAndFeel);
			SwingUtilities.updateComponentTreeUI(rootPane);
			this.currentLookAndFeel = lookAndFeel;
		} catch (UnsupportedLookAndFeelException ex) {
			Logger.getRootLogger().warn("LookAndFeel not found", ex);
		}

		setChanged();
		notifyObservers();
	}
}
