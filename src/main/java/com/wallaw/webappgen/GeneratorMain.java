package com.wallaw.webappgen;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wallaw.webappgen.util.FileHelper;
import com.wallaw.webappgen.util.GLogger;
import com.wallaw.webappgen.util.StringHelper;

/**
 * 应用程序入口
 */
public class GeneratorMain {
    public static void main(String[] args) throws Exception {


        System.out.println("开始生成：" + new Date());
        //检查设置
        if (StringHelper.isBlank(GenConfig.OUT_ROOT_DIR_STR))
        {
            System.out.print("[程序异常退出]未设置输出目录！");
            System.exit(0);
        }

        if (GenConfig.TEMPLATE_ROOT_DIR == null){
            System.out.print("[程序异常退出]未设置模板目录！");
            System.exit(0);
        }
        GLogger.println("模板目录[" + GenConfig.TEMPLATE_ROOT_DIR.getAbsolutePath() + "]  生成文件输出目录[" + new File(GenConfig.OUT_ROOT_DIR_STR).getAbsolutePath() + "]");

        FileHelper.deleteOutRootDir() ;  //删除生成器的输出目录
        Generator generator = new Generator();
        //generator.generateByDatabase();    //自动搜索数据库中的所有表并生成文件,template为模板的根目录
        List<String> tableNames = new ArrayList<String>();
        tableNames.add("xxx");
        tableNames.add("xxssa");
        tableNames.add("test");
        generator.generateByDatabase(tableNames);
        System.out.println("生成成功：" + new Date());
        //打开文件夹
        Runtime.getRuntime().exec("cmd.exe /c start " + GeneratorProperties.getRequiredProperty("outRoot"));
    }
}
