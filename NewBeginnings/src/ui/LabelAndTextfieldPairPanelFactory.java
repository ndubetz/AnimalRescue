package ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LabelAndTextfieldPairPanelFactory {

	public static JPanel buildPanel(String[] labels, String[] content) {
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
			panel.add(jLabel, constraints);
			JTextField jTextField = new JTextField(25);

			if (content[i] != null) {
				jTextField.setText(content[i]);
			}
			jTextField.setEditable(false);
			constraints.gridx = 1;
			panel.add(jTextField, constraints);
		}

		return panel;
	}
}
