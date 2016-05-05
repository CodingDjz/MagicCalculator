package person.djz.calculator.listener;

import javax.swing.*;

import person.djz.calculator.gui.MCFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Djz on 2016/4/9.
 *
 * 运算符监听器
 */
public class OperationButtonListener implements ActionListener {

	private JTextField printField;
	private String value;

	public OperationButtonListener(JTextField printField, String value) {
		this.printField = printField;
		this.value = value;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		printField.setText("0".equals(printField.getText()) ? value : printField.getText() + " "+value+" ");
	}
}
