package app.gui.settings.plaf;

import com.sun.java.swing.plaf.motif.MotifLookAndFeel;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class PlafSelectPanel extends JPanel {

	private final Map<String, LookAndFeel> LOOK_AND_FEEL_LIST = new TreeMap<String, LookAndFeel>() {{
		put("Metal", new MetalLookAndFeel());
		put("Motif", new MotifLookAndFeel());
		put("Nimbus", new NimbusLookAndFeel());
	}};

	public PlafSelectPanel() {
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

		JLabel jLabel = new JLabel("Pattern");
		jLabel.setMaximumSize(new Dimension(250, 400));
		jLabel.setHorizontalAlignment(SwingConstants.CENTER);

		List<PlafRadioButton> plafRadioButtons = LOOK_AND_FEEL_LIST.entrySet().stream()
				.map(entry -> new PlafRadioButton(entry.getKey(), entry.getValue(), new Dimension(200, 40)))
				.collect(Collectors.toList());

		plafRadioButtons.get(0).setSelected(true);

		ButtonGroup group = new ButtonGroup();
		plafRadioButtons.forEach(group::add);

		add(jLabel);
		add(Box.createRigidArea(new Dimension(100, 0)));
		plafRadioButtons.forEach(this::add);
	}
}
