package person.djz.calculator;

import java.awt.Button;

import javax.swing.JButton;

/**
 * Created by Djz on 2016/4/22.
 *
 * 单元实体类
 */
public class MCElement {
	/* 按钮显示字符 */
	private String btnText;
	/* 运算字符 */
	private String value;
	/* 元素格式类型 */
	private String btnType;
	private JButton button;

	public MCElement(String btnText, String value, String btnType) {
		this.btnText = btnText;
		this.value = value;
		this.btnType = btnType;
		button = new JButton(btnText);
	}

	public String getBtnText() {
		return btnText;
	}

	public void setBtnText(String btnText) {
		this.btnText = btnText;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getOptType() {
		return btnType;
	}

	public void setOptType(String btnType) {
		this.btnType = btnType;
	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}

}
