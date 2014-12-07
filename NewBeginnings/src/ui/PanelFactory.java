package ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import mainWindow.MainView;

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
		panel.setBackground(MainView.LIGHT_BLUE);
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
	 * @return a ButtonGroup with the specified buttons and the specified items
	 */
	public static ButtonGroup buildButtonGroup(List<String> items,
			Class<? extends AbstractButton> type) {
		ButtonGroup buttonGroup = new ButtonGroup();

		for (String item : items) {
			if (type.getClass().equals(JRadioButton.class)) {
				JRadioButton button = new JRadioButton();
				if (item != null) {
					button.setText(item);
				}
				button.setSelected(false);
				buttonGroup.add(button);
			} else if (type.getClass().equals(JCheckBox.class)) {
				JCheckBox checkBox = new JCheckBox(item);
				if (item != null) {
					checkBox.setText(item);
				}
				checkBox.setSelected(false);
				buttonGroup.add(checkBox);
			}
		}

		return buttonGroup;
	}

	public static JPanel buildTextFieldAndTextFieldPairPanel(String[] dates,
			String[] medicalInfo) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBackground(MainView.LIGHT_BLUE);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);

		for (int i = 0; i < dates.length; i++) {
			JTextField dateField = new JTextField(10);
			if (dates[i] != null) {
				dateField.setText(dates[i]);
			}
			dateField.setEditable(false);

			constraints.gridy = i;
			constraints.gridx = 0;
			constraints.anchor = GridBagConstraints.LINE_START;
			panel.add(dateField, constraints);
			JTextField infoField = new JTextField(40);

			if (medicalInfo[i] != null) {
				infoField.setText(medicalInfo[i]);
			}
			infoField.setEditable(false);
			constraints.gridx = 1;
			constraints.anchor = GridBagConstraints.LINE_END;
			panel.add(infoField, constraints);
		}

		return panel;
	}
}
