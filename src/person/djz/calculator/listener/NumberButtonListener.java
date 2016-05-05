package person.djz.calculator.listener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Djz on 2016/4/9.
 *
 * 数字按钮监听器
 */

public class NumberButtonListener implements ActionListener {
	private JTextField printField;
	private String number;

	public NumberButtonListener(JTextField printField, String number) {
		this.printField = printField;
		this.number = number;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String printStr = printField.getText();
		if (printStr.equals("0")) {
			printStr = String.valueOf(number);
		} else {
			printStr += String.valueOf(number);
		}
		// JOptionPane.showConfirmDialog(null, "然，我其实....");
		printField.setText(printStr);
	}
}
