package com.wallaw.webappgen;

import com.wallaw.webappgen.util.FreemarkerHelper;
import com.wallaw.webappgen.util.GLogger;
import com.wallaw.webappgen.util.StringHelper;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: 肖明 xiaoming8484@gmail.com
 * Date: 13-7-13
 * Time: 下午11:03
 * To change this template use File | Settings | File Templates.
 */
public class GeneratorHelper {

    public static boolean isFoundInsertLocation(Template template, Map model, File outputFile, StringWriter newFileContent) throws IOException, TemplateException {
        LineNumberReader reader = new LineNumberReader(new FileReader(outputFile));
        String line = null;
        boolean isFoundInsertLocation = false;

        //FIXME 持续性的重复生成会导致out of memory
        PrintWriter writer = new PrintWriter(newFileContent);
        while ((line = reader.readLine()) != null) {
            writer.println(line);
            // only insert once
            if (!isFoundInsertLocation && line.indexOf(GenConfig.GENERATOR_INSERT_LOCATION) >= 0) {
                template.process(model, writer);
                writer.println();
                isFoundInsertLocation = true;
            }
        }

        writer.close();
        reader.close();
        return isFoundInsertLocation;
    }

    /**
     * 模板核心设置
     *
     * @param templateRootDirs
     * @param defaultEncoding
     * @param templateName
     * @return
     * @throws IOException
     */
    public static Configuration newFreeMarkerConfiguration(File templateRootDirs, String defaultEncoding, String templateName) throws IOException {
        Configuration conf = new Configuration();

        FileTemplateLoader[] templateLoaders = new FileTemplateLoader[1];//只有一个模板目录
        templateLoaders[0] = new FileTemplateLoader(templateRootDirs);
        MultiTemplateLoader multiTemplateLoader = new MultiTemplateLoader(templateLoaders);

        conf.setTemplateLoader(multiTemplateLoader);
        conf.setNumberFormat("###############");
        conf.setBooleanFormat("true,false");
        conf.setDefaultEncoding(defaultEncoding);
        List<String> autoIncludes = getParentPaths(templateName, "macro.include");
        List<String> availableAutoInclude = FreemarkerHelper.getAvailableAutoInclude(conf, autoIncludes);
        conf.setAutoIncludes(availableAutoInclude);
        GLogger.trace("set Freemarker.autoIncludes:" + availableAutoInclude + " for templateName:" + templateName + " autoIncludes:" + autoIncludes);
        return conf;
    }

    public static List<String> getParentPaths(String templateName, String suffix) {
        String array[] = StringHelper.tokenizeToStringArray(templateName, "\\/");
        List<String> list = new ArrayList<String>();
        list.add(suffix);
        list.add(File.separator + suffix);
        String path = "";
        for (int i = 0; i < array.length; i++) {
            path = path + File.separator + array[i];
            list.add(path + File.separator + suffix);
        }
        return list;
    }
}
