package person.djz.calculator.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import person.djz.calculator.MCElement;
import person.djz.calculator.MCUtil;
import person.djz.calculator.MCUtil.UIText;
import person.djz.calculator.listener.NumberButtonListener;
import person.djz.calculator.listener.OperationButtonListener;
import person.djz.calculator.listener.SpecialButtonListener;

/**
 * Created by Djz on 2016/3/10.
 */
public class MCFrame extends JFrame {

	private JButton clear = new JButton("清");
	private JButton equal = new JButton("得");
	private JButton pound = new JButton("井");
	private JPanel printLine = new JPanel();
	private JPanel inputArea = new JPanel();
	private JPanel numArea = new JPanel();
	private JPanel optArea = new JPanel();
	private JTextField printfield = new JTextField(19);
	private JLabel label = new JLabel();
	private String printStr = "";
	private int printText;
	private int secondNum;

	public MCFrame() {
		numBtnOpt();
		optBtnOpt();
		speBtnOpt();
		setLayout();
		MCUtil.printLog("界面初始化完成");
	}

	/**
	 * 布局设置
	 */
	public void setLayout() {

		MCUtil.setSentence(label);
		add(printLine);
		add(inputArea, BorderLayout.SOUTH);
		printLine.add(printfield);
		// printLine.setBackground(new Color(0xcc, 0xcc, 0xcc));
		printLine.add(label, BorderLayout.NORTH);
		// 句子字体设置
		label.setFont(new Font("微软雅黑", 0, 16));
		inputArea.add(numArea);
		inputArea.add(optArea);
		numArea.setLayout(new GridLayout(4, 3, 5, 10));
		optArea.setLayout(new GridLayout(4, 2, 10, 10));
		numArea.setBorder(BorderFactory.createEtchedBorder());
		optArea.setBorder(BorderFactory.createEtchedBorder());
		inputArea.setBorder(BorderFactory.createEtchedBorder());
		printLine.setBorder(BorderFactory.createEtchedBorder());
		printfield.setText("0");
		// 显示条字体设置
		printfield.setPreferredSize(new Dimension(0, 32));
		printfield.setFont(new Font("微软雅黑", 0, 18));
		printfield.setHorizontalAlignment(JTextField.RIGHT);// 文本右对齐
		printfield.setEditable(false);
		printfield.setBackground(new Color(255, 255, 255));
		setTitle("魔法计算器");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		AreaLayout();
		setSize(360, 380);
		setLocation(getToolkit().getScreenSize().width * 3 / 8, getToolkit().getScreenSize().height * 2 / 8);
		setVisible(true);
		// pack();
	}

	/*
	 * 数字按钮操作，设置按钮的监听操作
	 */
	public void numBtnOpt() {
		Iterator iter = MCUtil.getIter(MCUtil.numEleMap);
		while (iter.hasNext()) {
			MCElement MCele = (MCElement) iter.next();
			JButton numBtn = MCele.getButton();
			numBtn.setPreferredSize(new Dimension(60, 50));
			String value = MCele.getValue();
			numBtn.addActionListener(new NumberButtonListener(printfield, value));
		}
	}

	/*
	 * 运算按钮操作 ，设置按钮的监听操作
	 */
	public void optBtnOpt() {
		Iterator iter = MCUtil.getIter(MCUtil.optEleMap);
		while (iter.hasNext()) {
			MCElement MCele = (MCElement) iter.next();
			JButton optBtn = MCele.getButton();
			optBtn.setPreferredSize(new Dimension(25, 50));
			String value = MCele.getValue();
			optBtn.addActionListener(new OperationButtonListener(printfield, value));
		}
	}

	public void speBtnOpt() {
		Iterator iter = MCUtil.getIter(MCUtil.speEleMap);
		while (iter.hasNext()) {
			MCElement MCele = (MCElement) iter.next();
			JButton speBtn = MCele.getButton();
			String value = MCele.getValue();
			speBtn.addActionListener(new SpecialButtonListener(printfield, value, label));
		}
	}

	/*
	 * 将按钮添加到对应布局位置
	 */
	public void AreaLayout() {
		HashMap<String, MCElement> eleMap = MCUtil.MCEleMap;
		ArrayList<String> numList = MCUtil.listMap.get(MCUtil.NUM_AREA);
		ArrayList<String> optList = MCUtil.listMap.get(MCUtil.OPT_AREA);
		JButton jbtn;
		// 数字区布局
		for (int i = 0; i < numList.size(); i++) {
			jbtn = eleMap.get(UIText.getString(numList.get(i))).getButton();
			jbtn.setFont(new Font("黑体", 1, 19));
			numArea.add(jbtn);
		}
		// 操作区布局
		for (int j = 0; j < optList.size(); j++) {
			jbtn = eleMap.get(UIText.getString(optList.get(j))).getButton();
			jbtn.setFont(new Font("黑体", 1, 16));
			optArea.add(jbtn);
		}
		MCUtil.printLog("按钮已加入布局设置");
	}
}
