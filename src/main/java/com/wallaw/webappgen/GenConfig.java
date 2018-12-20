package com.wallaw.webappgen;

import com.wallaw.webappgen.util.FileHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 生成工具相关配置
 * Created with IntelliJ IDEA.
 * User: 肖明 xiaoming8484@gmail.com
 * Date: 13-7-12
 * Time: 下午10:29
 * To change this template use File | Settings | File Templates.
 */
public  class  GenConfig {
    public static final String TEMPLATE_ROOT_DIR_STR= "template";//模板目录字符串
    public static final File TEMPLATE_ROOT_DIR=new File(TEMPLATE_ROOT_DIR_STR).getAbsoluteFile(); //模板目录对象
    public static final String OUT_ROOT_DIR_STR  = GeneratorProperties.getProperty("outRoot");//输出目录字符串
    public static final List IGNORE_LIST = new ArrayList();  //排除的目录
    public static final String  EXTENSIONS = System.getProperty("generator.removeExtensions", ".ftl,.vm"); //模板后缀名
    public static final String SOURCE_ENCODING = System.getProperty("generator.sourceEncoding", "UTF-8");//文件编码
    public static final String GENERATOR_INSERT_LOCATION = "generator-insert-location"; //增量文件合并的标识

    public static final String INCLUDES = System.getProperty("generator.includes"); // 需要处理的模板，使用逗号分隔符,示例值: java_src/**,java_test/**
    public static final String EXCLUDES = System.getProperty("generator.excludes"); // 不需要处理的模板，使用逗号分隔符,示例值: java_src/**,java_test/**

    public static final List<File> TEMPLATE_FILES = FileHelper.getTemplateFiles(GenConfig.TEMPLATE_ROOT_DIR); //所有模板文件列表

    static {
        IGNORE_LIST.add(".svn");
        IGNORE_LIST.add("CVS");
        IGNORE_LIST.add(".cvsignore");
        IGNORE_LIST.add(".copyarea.db"); //ClearCase
        IGNORE_LIST.add("SCCS");
        IGNORE_LIST.add("vssver.scc");
        IGNORE_LIST.add(".DS_Store");
        IGNORE_LIST.add(".git");
        IGNORE_LIST.add(".gitignore");
    }

}
