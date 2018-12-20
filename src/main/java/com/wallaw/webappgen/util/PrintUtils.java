package com.wallaw.webappgen.util;

import com.wallaw.webappgen.GeneratorProperties;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;

/**
 * 日志处理类，将异常日志输出到文件
 * Created with IntelliJ IDEA.
 * User: 肖明 xiaoming8484@gmail.com
 * Date: 13-7-13
 * Time: 下午11:24
 * To change this template use File | Settings | File Templates.
 */
public class PrintUtils {

    public static void printExceptionsSumary(List<Exception> exceptions) throws FileNotFoundException {
        String msg = "";
        File errorFile = new File(GeneratorProperties.getProperty("outRoot"),"generator_error.log");
        if(exceptions != null && exceptions.size() > 0) {
            System.err.println("[Generate Error Summary] : "+msg);
            PrintStream output = new PrintStream(new FileOutputStream(errorFile));
            for(int i = 0; i < exceptions.size(); i++) {
                Exception e = exceptions.get(i);
                System.err.println("[GENERATE ERROR]:"+e);
                if(i == 0) e.printStackTrace();
                e.printStackTrace(output);
            }
            output.close();
            System.err.println("***************************************************************");
            System.err.println("* "+"* 输出目录已经生成generator_error.log用于查看错误 ");
            System.err.println("***************************************************************");
        }
    }
}
