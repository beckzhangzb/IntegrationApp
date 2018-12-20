package com.wallaw.webappgen;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wallaw.webappgen.provider.db.table.TableFactory;
import com.wallaw.webappgen.provider.db.table.model.Table;
import com.wallaw.webappgen.util.*;

/**
 * 代码生成器核心引擎
 * <p/>
 * 主要提供以下两个方法供外部使用
 * <pre>
 * generateBy() 用于生成文件
 * deleteBy() 用于删除生成的文件
 * </pre>
 *
 * @author xiaoming
 * @email xiaoming(a)wallaw.com.cn
 */
public class Generator {

    /**
     * 搜索数据库中所有表，生成文件 ，全量生成
     * @throws Exception
     */
    public void generateByDatabase() throws Exception {
        List<Table> tables = TableFactory.getInstance().getAllTables();
        System.out.println("["+new Date()+"]getAllTables End");
        List exceptions = new ArrayList();
        for (int i = 0; i < tables.size(); i++) {
            try {
                generateByTable(tables.get(i));
            } catch (GeneratorException ge) {
                exceptions.addAll(ge.getExceptions());
            }
        }
        PrintUtils.printExceptionsSumary(exceptions);
    }

    /**
     * 搜索数据库中所有表，根据表名称生成文件 ，生成指定表
     * @throws Exception
     */
    public void generateByDatabase(List<String> tableNames) throws Exception {
        List<Table> tables = TableFactory.getInstance().getAllTables();
        System.out.println("["+new Date()+"]getAllTables End");
        List exceptions = new ArrayList();
        for (int i = 0; i < tables.size(); i++) {
            Table table = tables.get(i);
            if(tableNames.contains(table.getSqlName())){
                try {
                    generateByTable(tables.get(i));
                } catch (GeneratorException ge) {
                    exceptions.addAll(ge.getExceptions());
                }
            }
        }
        PrintUtils.printExceptionsSumary(exceptions);
    }

    /**
     * 根据单张表的数据，生成文件  ，单表生成
     * @param table
     * @throws Exception
     */
    private void generateByTable(Table table) throws Exception {
        GeneratorModel generatorModel = GeneratorModelUtils.newFromTable(table);

        GeneratorException ge = new GeneratorException("generator occer error, Generator BeanInfo:" + BeanHelper.describe(this));
        List<Exception> exceptions = new ArrayList();
        for (File templateFile : GenConfig.TEMPLATE_FILES) {
            try {
                new TemplateProcessor().executeGenerate(generatorModel.templateModel, generatorModel.filePathModel, templateFile);
            } catch (Exception e) {
                GLogger.warn("iggnore generate error,template is:" + templateFile + " cause:" + e);
                exceptions.add(e);
            }
        }
        ge.addAll(exceptions);
        if (!ge.exceptions.isEmpty()) throw ge;
    }

    public static class GeneratorModel implements java.io.Serializable {
        public Map filePathModel;
        public Map templateModel;

        public GeneratorModel(Map templateModel, Map filePathModel) {
            this.templateModel = templateModel;
            this.filePathModel = filePathModel;
        }
    }
}
