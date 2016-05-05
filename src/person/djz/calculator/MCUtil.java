package person.djz.calculator;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;

import javax.swing.JLabel;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.sun.crypto.provider.ARCFOURCipher;

/**
 * Created by Djz on 2016/4/22.
 *
 * 工具类
 */

public class MCUtil {
	public static final String NUM_AREA = "Area_num";
	public static final String OPT_AREA = "Area_opt";
	public static final HashMap<String, MCElement> MCEleMap = new HashMap<>();
	public static final HashMap<String, MCElement> numEleMap = new HashMap<>();
	public static final HashMap<String, MCElement> optEleMap = new HashMap<>();
	public static final HashMap<String, MCElement> speEleMap = new HashMap<>();
	public static final HashMap<String, ArrayList<String>> listMap = new HashMap<>();
	public static final HashMap<String, String> pwdMap = new HashMap<>();
	public static final ArrayList<MCSentence> sList = new ArrayList<>();

	public enum UIText {
		ZERO("zero"), ONE("one"), TWO("two"), THREE("three"), FOUR("four"), FIVE("five"), SIX("six"), SEVEN(
				"seven"), EIGHT("eight"), NINE("nine"), ADD("add"), POINT("point"), SUB("sub"), MULT("mult"), DIVI(
						"divi"), EQUAL("equal"), POUND("pound"), CLEAR("clear"), DELETE("delete"), FUNC("function");
		private String text;

		private UIText(String text) {
			this.text = text;
		}

		public static String getString(String key) {
			String value = "";
			switch (key) {
			case ".":
				value = POINT.toString();
				break;
			case "+":
				value = ADD.toString();
				break;
			case "-":
				value = SUB.toString();
				break;
			case "x":
				value = MULT.toString();
				break;
			case "÷":
				value = DIVI.toString();
				break;
			case "0":
				value = ZERO.toString();
				break;
			case "1":
				value = ONE.toString();
				break;
			case "2":
				value = TWO.toString();
				break;
			case "3":
				value = THREE.toString();
				break;
			case "4":
				value = FOUR.toString();
				break;
			case "5":
				value = FIVE.toString();
				break;
			case "6":
				value = SIX.toString();
				break;
			case "7":
				value = SEVEN.toString();
				break;
			case "8":
				value = EIGHT.toString();
				break;
			case "9":
				value = NINE.toString();
				break;
			case "=":
				value = EQUAL.toString();
				break;
			case "#":
				value = POUND.toString();
				break;
			case "c":
				value = CLEAR.toString();
				break;
			case "d":
				value = DELETE.toString();
				break;
			case "f":
				value = FUNC.toString();
				break;
			}
			return value;
		}
	}

	/**
	 * 读取XML文件，返回ROOT节点
	 * 
	 * @param path
	 * @return
	 */
	public static Element loadXML(String path) {
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(System.getProperty("user.dir") + path);
		} catch (DocumentException e) {
			printLog("-----读取XML异常-----");
			e.printStackTrace();
		}
		return document.getRootElement();
	}

	/**
	 * 读取Prop文件，返回properties
	 * 
	 * @param path
	 * @return
	 */
	public static Properties loadProp(String path) {
		Properties prop = new Properties();
		String filePath = System.getProperty("user.dir") + path;
		try {
			prop.load(new InputStreamReader(new FileInputStream(new File(filePath)), "utf-8"));
			printLog(filePath);
		} catch (IOException e) {
			printLog("-----读取properties文件异常-----");
			e.printStackTrace();
		}
		return prop;
	}
	/**
	 * 获得Map迭代器
	 * @param map
	 * @return
	 */
	public static Iterator getIter(HashMap map) {
		return map.values().iterator();

	}
	public static void setSentence(JLabel label){		
		int index = new Random().nextInt(MCUtil.sList.size());
		MCSentence s = sList.get(index);
		String p1 = s.getP1();
		String p2 = s.getP2();
		label.setText("<html><p>" + p1 + "</p><p>" + p2 + "</p></html>");
	}

	/**
	 * 打印日志
	 * 
	 * @param log
	 */
	public static void printLog(String log) {
		System.out.println("-----" + log + "-----");
	}

}
