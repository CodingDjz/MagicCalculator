package person.djz.calculator;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.tools.StandardJavaFileManager;

import java.util.Properties;

import org.dom4j.Element;

import com.sun.xml.internal.bind.util.Which;

import person.djz.calculator.MCUtil.UIText;

public class MCInitor {
	private static MCInitor INSTENCE = null;

	/**
	 * 获得初始化器实例
	 * 
	 * @return
	 */
	public static MCInitor getINSTENCE() {
		if (INSTENCE == null) {
			INSTENCE = new MCInitor();
		}
		return INSTENCE;
	}

	/**
	 * 初始化
	 * 
	 * @param confPropPath
	 */
	public void initMC(String confPropPath, String layoutPath) {
		loadMceleMap(confPropPath);
		MCUtil.printLog("加载config.properties完毕");
		loadLayoutXML(layoutPath);
		MCUtil.printLog("加载layout.xml完毕");
		loadPwdProp();
		MCUtil.printLog("加载System64_86完毕");
		loadSentenceXML();
		MCUtil.printLog("加载sentence.xml完毕");
	}

	/**
	 * 读取config.properties中的按钮文本，并将各类按钮加入相应map中
	 */
	public void loadMceleMap(String propPath) {
		Properties prop = MCUtil.loadProp(propPath);
		Iterator iter = prop.entrySet().iterator();
		String key = "";
		String btnText = "";
		String btnType = "";
		while (iter.hasNext()) {
			Map.Entry entry = (Entry) iter.next();
			btnText = (String) entry.getValue();
			key = getValue((String) entry.getKey());
			/* 将各种按钮进行分类：生成对象那个并加入map中 */
			switch (key) {
			case "+":
			case "-":
			case "x":
			case "÷":
				btnType = "opt";
				break;
			case "0":
			case "1":
			case "2":
			case "3":
			case "4":
			case "5":
			case "6":
			case "7":
			case "8":
			case "9":
				btnType = "num";
				break;
			case "=":
			case "#":
			case "c":
			case "d":
			case ".":
			case "f":
				btnType = "spe";
				break;
			default:
				btnType = "nothing";
				break;
			}
			MCElement ele = new MCElement(btnText, key, btnType);
			if ("opt".equals(btnType))
				MCUtil.optEleMap.put(UIText.getString(key), ele);
			if ("num".equals(btnType))
				MCUtil.numEleMap.put(UIText.getString(key), ele);
			if ("spe".equals(btnType))
				MCUtil.speEleMap.put(UIText.getString(key), ele);

			MCUtil.MCEleMap.put(UIText.getString(key), ele);
		}
	}

	/**
	 * 加载布局规范文件 layout.xml
	 * 
	 * @param layoutPath
	 */
	public void loadLayoutXML(String layoutPath) {
		Element root = MCUtil.loadXML(layoutPath);
		Iterator nodeIter = root.elements().iterator();
		// 区域节点
		while (nodeIter.hasNext()) {
			Element ele1 = (Element) nodeIter.next();
			String nodeName = ele1.getName();
			Iterator iter = ele1.elements().iterator();
			ArrayList<String> list = new ArrayList<>();
			while (iter.hasNext()) {
				Element ele2 = (Element) iter.next();
				String val = ele2.attributeValue("val");
				list.add(val);
			}
			MCUtil.listMap.put(nodeName, list);
		}

	}

	public void loadPwdProp() {
		Properties prop = MCUtil.loadProp(File.separator + "lib" + File.separator + "System64_86.properties");
		Iterator iter = prop.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, String> entry = (Entry<String, String>) iter.next();
			MCUtil.pwdMap.put(entry.getKey(), entry.getValue());

		}
	}

	public void loadSentenceXML() {
		Element root = MCUtil.loadXML(File.separator + "config" + File.separator + "sentence.xml");
		Iterator iter = root.elementIterator();
		while (iter.hasNext()) {
			Element sEle = (Element) iter.next();
			String nodeName = sEle.getName();
			Iterator iter2 = sEle.elementIterator();
			String p1 = sEle.elementText("p1");
			String p2 = sEle.elementText("p2");
			MCSentence s = new MCSentence(p1, p2);
			MCUtil.sList.add(s);
		}
	}

	/**
	 * 转换配置文件中的key值表示
	 * 
	 * @param key
	 * @return
	 */
	public String getValue(String key) {
		if (key.startsWith("\"")) {
			key = key.substring(key.indexOf("\"") + 1, key.lastIndexOf("\""));
		} else {
			switch (key) {
			case "等号键":
				key = "=";
				break;
			case "井号键":
				key = "#";
				break;
			case "清零键":
				key = "c";
				break;
			case "删除键":
				key = "d";
				break;
			case "小数点":
				key = ".";
				break;
			case "功能键":
				key = "f";
				break;
			}
		}
		return key;
	}

}
