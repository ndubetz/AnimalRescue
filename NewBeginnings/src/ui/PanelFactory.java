package ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelFactory {

	/**
	 * buildLabelAndTextFieldPairPanel takes two arrays of Strings with the same
	 * length, one array for label text and one for textfield text, and returns
	 * a JPanel consisting of labels paired with textfields populated with the
	 * specified content.
	 * 
	 * @param labels
	 * @param content
	 * @return JPanel labels paired with textfields
	 */

	public static JPanel buildLabelAndTextFieldPairPanel(String[] labels,
			String[] content) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBackground(new Color(201, 226, 233));
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);

		for (int i = 0; i < labels.length; i++) {
			JLabel jLabel = new JLabel();
			jLabel.setText(labels[i] + ": ");
			constraints.gridy = i;
			constraints.gridx = 0;
			constraints.anchor = GridBagConstraints.LINE_START;
			panel.add(jLabel, constraints);
			JTextField jTextField = new JTextField(25);

			if (content[i] != null) {
				jTextField.setText(content[i]);
			}
			jTextField.setEditable(false);
			constraints.gridx = 1;
			constraints.anchor = GridBagConstraints.LINE_END;
			panel.add(jTextField, constraints);
		}

		return panel;
	}

	/**
	 * buildButtonGroup takes a List of Strings and returns a ButtonGroup with
	 * the specified type of button, each one mapped to a List item
	 * 
	 * @param items
	 * @param type
	 *            - must be a child of AbstractButton
	 * @return
	 */
	public static ButtonGroup buildButtonGroup(List<String> items,
			Class<? extends AbstractButton> type) {
		return new ButtonGroup();
	}
}
