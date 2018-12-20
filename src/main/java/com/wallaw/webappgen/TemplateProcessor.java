package com.wallaw.webappgen;

import com.wallaw.webappgen.util.*;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: 肖明 xiaoming8484@gmail.com
 * Date: 13-7-13
 * Time: 下午11:02
 * To change this template use File | Settings | File Templates.
 */
public class TemplateProcessor {

    private String outputFile;

    public static Map<String,Template> templateMaps = new HashMap<String,Template>();

    /**
     * 生成文件
     *
     * @param templateModel
     * @param filePathModel
     * @param templateFile  模板文件
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     * @throws freemarker.template.TemplateException
     *
     */
    public void executeGenerate(Map templateModel, Map filePathModel, File templateFile){
        String templateRelativePath = FileHelper.getRelativePath(GenConfig.TEMPLATE_ROOT_DIR, templateFile);  //模板文件相对路径
        try {
            String outputFileRelativePath = proceeForOutputFilepath(filePathModel, templateRelativePath);
            setOutputFile(outputFileRelativePath);//TODO: 这里的处理方式也需要更改
            generateNewFileOrInsertIntoFile(templateRelativePath, getOutputFile(), templateModel);
        } catch (Exception e) {
            throw new RuntimeException("generate oucur error,templateFile is:" + templateFile + " => " + getOutputFile() + " cause:" + e, e);
        }
    }

    /**
     * 获取输出文件路径
     *
     * @param filePathModel
     * @param templateFile
     * @return
     * @throws IOException
     */
    private String proceeForOutputFilepath(Map filePathModel, String templateFile) throws IOException {
        String outputFilePath = templateFile;
        for (String removeExtension : GenConfig.EXTENSIONS.split(",")) {
            if (outputFilePath.endsWith(removeExtension)) {
                outputFilePath = outputFilePath.substring(0, outputFilePath.length() - removeExtension.length()); //去除模板自带的后缀
                break;
            }
        }
        Configuration conf = GeneratorHelper.newFreeMarkerConfiguration(GenConfig.TEMPLATE_ROOT_DIR, GenConfig.SOURCE_ENCODING, "/filepath/processor/");
        return FreemarkerHelper.processTemplateString(outputFilePath, filePathModel, conf);
    }


    /**
     * 获取模板
     *
     * @param templateName
     * @return
     * @throws IOException
     */
    private Template getTemplate(String templateName) throws IOException {
        if(templateMaps.get(templateName)!=null){
            return  templateMaps.get(templateName);
        }else{
            templateMaps.put(templateName,GeneratorHelper.newFreeMarkerConfiguration(GenConfig.TEMPLATE_ROOT_DIR, GenConfig.SOURCE_ENCODING, templateName).getTemplate(templateName));
            return templateMaps.get(templateName);
        }
    }

    /**
     * update by xiaoming@wallaw.com.cn
     * update 2013-07-13
     * 根据模板和数据生成最终文件
     *
     * @param templateFileRelativePath  模板文件相对路径
     * @param targetFileAbsolutePath    输出目标文件绝对路径
     * @param templateModel 数据文件
     * @throws Exception
     */
    private void generateNewFileOrInsertIntoFile(String templateFileRelativePath, String targetFileAbsolutePath, Map templateModel) throws Exception {
        Template template = getTemplate(templateFileRelativePath);
        template.setOutputEncoding(GenConfig.SOURCE_ENCODING);

        File targetFile = new File(targetFileAbsolutePath);
        if (targetFile.exists()) {
            StringWriter newFileContentCollector = new StringWriter();
            if (GeneratorHelper.isFoundInsertLocation(template, templateModel, targetFile, newFileContentCollector)) {
                GLogger.println("[insert]\t generate content into:" + targetFileAbsolutePath);
                IOHelper.saveFile(targetFile, newFileContentCollector.toString(), GenConfig.SOURCE_ENCODING);
                GLogger.println("[not generate]\t by gg.isOverride()=false and targetFile exist:" + targetFileAbsolutePath);
                return;
            }
            //TODO: 这里的处理方式有点怪异
            GLogger.println("[not generate]\t by gg.isOverride()=false and targetFile exist:" + targetFileAbsolutePath);
            return;
        }

        FileHelper.parentMkdir(targetFile);
        GLogger.println("[generate]\t template:" + templateFileRelativePath + " ==> " + targetFileAbsolutePath);
        FreemarkerHelper.processTemplate(template, templateModel, targetFile, GenConfig.SOURCE_ENCODING);
    }

    public String getOutputFile() {
        if (outputFile != null && new File(outputFile).isAbsolute()) {
            return outputFile;
        } else {
            return new File(GenConfig.OUT_ROOT_DIR_STR, outputFile).getAbsolutePath();
        }
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }
}
