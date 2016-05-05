package person.djz.calculator.run;

import java.io.File;

import person.djz.calculator.MCInitor;
import person.djz.calculator.MCUtil;
import person.djz.calculator.gui.MCFrame;

/**
 * Created by Djz on 2016/3/10.
 */
public class run {
    public static void main(String[] args){
    	MCInitor mcInit = MCInitor.getINSTENCE();
    	mcInit.initMC(File.separator+"config"+File.separator+"config.properties", File.separator+"config"+File.separator+"layout.xml");
    	MCUtil.printLog("配置文件初始化完毕");
    	MCUtil.printLog("准备启动计算器键盘");
    	MCFrame myFrame = new MCFrame();
    }
}
