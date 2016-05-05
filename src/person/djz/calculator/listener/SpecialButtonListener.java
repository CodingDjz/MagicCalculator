package person.djz.calculator.listener;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.management.RuntimeErrorException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import person.djz.calculator.MCUtil;

public class SpecialButtonListener implements ActionListener {

	private JTextField printField;
	private String btnType;
	private JLabel label;
	Random rd;

	public SpecialButtonListener(JTextField printfield, String btnType, JLabel label) {
		super();
		this.printField = printfield;
		this.btnType = btnType;
		this.label = label;
		rd = new Random();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String printStr = printField.getText();
		switch (btnType) {
		case "d":
			if (!printStr.equals("0"))
				printStr = printStr.substring(0, printStr.length() - 1);
			if (printStr.equals(""))
				printStr = "0";
			break;
		case "c":
			printStr = "0";
			break;
		case "#":
			poundAction(printStr);
			break;
		case ".":
			printStr += ".";
			break;
		case "=":
			MCUtil.setSentence(label);
			printStr = equalAction(printStr);
			break;
		case "f":

			break;
		}
		printField.setText(printStr);
	}

	/**
	 * 井号键响应方法
	 * 
	 * @param printStr
	 */
	public void poundAction(String printStr) {
		String savePWD = MCUtil.pwdMap.get("save_pwd");
		String musicPWD = MCUtil.pwdMap.get("music_pwd");
		String existPath = MCUtil.pwdMap.get("save_path");
		String baseDir = System.getProperty("user.dir");
		if (printStr.equals(savePWD)) {
			if (existPath != null) {
				openExistDir(existPath);
			} else {
				int saveHeight = Integer.parseInt(MCUtil.pwdMap.get("save_height"));
				int saveWidth = Integer.parseInt(MCUtil.pwdMap.get("save_width"));
				// 目录树位置
				String rootPath = baseDir + File.separator + "data" + File.separator + "document";
				// 生成目录
				String savePath = generateDir(saveHeight, saveWidth, rootPath);
				// 将目录写入System64_86.properties文件中
				writeSavePath(savePath, baseDir);

			}
		} else if (printStr.equals(musicPWD)) {
			try {
				Runtime.getRuntime().exec(baseDir + File.separator + "data" + File.separator + "data"+File.separator+"sfzc.mp3");
			} catch (IOException e) {
				MCUtil.printLog("运行文件时出错");
				e.printStackTrace();
			}
		}
	}

	/*
	 * 生成目录
	 */
	public String generateDir(int saveHeight, int saveWidth, String savePath) {
		String[] folderName = { "Program File", "Program File(x86)", "Window", "Temp", "Administrator", "data" };
		for (int i = 0; i <= saveHeight; i++) {
			int fCount = rd.nextInt(saveWidth);
			String fileName = "";
			for (int j = 0; j <= fCount; j++) {
				fileName = folderName[rd.nextInt(folderName.length)];
				String dirPath = savePath + File.separator + fileName;
				File file = new File(dirPath);
				if (file.getParentFile().exists()) {
					file.mkdir();
				}
			}
			savePath += File.separator + fileName;
		}
		MCUtil.printLog(savePath);
		return savePath;
	}

	/*
	 * 进入已打开目录
	 */
	public void openExistDir(String existPath) {
		try {
			Desktop.getDesktop().open(new File(existPath));
			MCUtil.printLog("进入已存在目录");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 将路径写入文件
	 */
	public void writeSavePath(String savePath, String baseDir) {
		try (FileWriter fos = new FileWriter(
				baseDir + File.separator + "lib" + File.separator + "System64_86.properties", true)) {
			fos.write("\nsave_path=" + savePath.replace("\\", "\\\\"));
			Desktop.getDesktop().open(new File(savePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 等号键响应方法
	 * 
	 * @param printStr
	 * @return
	 */
	public String equalAction(String printStr) {
		try {
			if (!printStr.equals("0")) {
				Double firstNum = Double.parseDouble(printStr.substring(0, printStr.indexOf(" ")));
				String optSymbol = printStr.substring(printStr.indexOf(" ") + 1, printStr.lastIndexOf(" "));
				String aString = printStr.substring(printStr.lastIndexOf(" ") + 1, printStr.length());
				Double secondNum = Double.parseDouble(aString);
				switch (optSymbol) {
				case "+":
					printStr = String.valueOf(firstNum + secondNum);
					break;
				case "-":
					printStr = String.valueOf(firstNum + (-secondNum));
					break;
				case "x":
					printStr = String.valueOf(firstNum * secondNum);
					break;
				case "÷":
					printStr = String.valueOf(firstNum / secondNum);
					break;
				}
			}
		} catch (Exception e) {
			// e.setStackTrace(null);
			Font font = new Font("微软雅黑", Font.BOLD, 14);
			UIManager.put("Label.font", font);
			UIManager.put("Button.font", font);
			JOptionPane.showMessageDialog(null, "算式格式有误，请重新输入...\n(具体格式请翻阅小学数学课本)\n玩笑 ^_^");
		}
		return printStr;
	}

	// public void changeLabel() {
	// MCSentence s = MCUtil.getRandomMCSentence();
	// String p1 = s.getP1();
	// String p2 = s.getP2();
	// label.setText("<html><p>" + p1 + "</p><p>" + p2 + "</p></html>");
	//
	// }
}
