package com.wallaw.webappgen;

import com.wallaw.webappgen.provider.db.sql.model.Sql;
import com.wallaw.webappgen.provider.db.table.model.Table;
import com.wallaw.webappgen.provider.java.model.JavaClass;
import com.wallaw.webappgen.util.BeanHelper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: 肖明 xiaoming8484@gmail.com
 * Date: 13-7-13
 * Time: 下午11:27
 * To change this template use File | Settings | File Templates.
 */
public class GeneratorModelUtils {

    public static Generator.GeneratorModel newFromTable(Table table) {
        Map templateModel = new HashMap();
        templateModel.put("table", table);
        setShareVars(templateModel);

        Map filePathModel = new HashMap();
        setShareVars(filePathModel);
        filePathModel.putAll(BeanHelper.describe(table));
        return new Generator.GeneratorModel(templateModel,filePathModel);
    }

    public static Generator.GeneratorModel newFromSql(Sql sql) throws Exception {
        Map templateModel = new HashMap();
        templateModel.put("sql", sql);
        setShareVars(templateModel);

        Map filePathModel = new HashMap();
        setShareVars(filePathModel);
        filePathModel.putAll(BeanHelper.describe(sql));
        return new Generator.GeneratorModel(templateModel,filePathModel);
    }

    public static Generator.GeneratorModel newFromClass(Class clazz) {
        Map templateModel = new HashMap();
        templateModel.put("clazz", new JavaClass(clazz));
        setShareVars(templateModel);

        Map filePathModel = new HashMap();
        setShareVars(filePathModel);
        filePathModel.putAll(BeanHelper.describe(new JavaClass(clazz)));
        return new Generator.GeneratorModel(templateModel,filePathModel);
    }

    public static Generator.GeneratorModel newFromMap(Map params) {
        Map templateModel = new HashMap();
        templateModel.putAll(params);
        setShareVars(templateModel);

        Map filePathModel = new HashMap();
        setShareVars(filePathModel);
        filePathModel.putAll(params);
        return new Generator.GeneratorModel(templateModel,filePathModel);
    }

    public static void setShareVars(Map templateModel) {
        templateModel.putAll(GeneratorProperties.getProperties());
        templateModel.putAll(System.getProperties());
        templateModel.put("env", System.getenv());
        templateModel.put("now", new Date());
        templateModel.putAll(GeneratorContext.getContext());
    }


    /** 生成器的上下文，存放的变量将可以在模板中引用 */
    private static class GeneratorContext {
        static ThreadLocal<Map> context = new ThreadLocal<Map>();
        public static void clear() {
            Map m = context.get();
            if(m != null) m.clear();
        }
        public static Map getContext() {
            Map map = context.get();
            if(map == null) {
                setContext(new HashMap());
            }
            return context.get();
        }
        public static void setContext(Map map) {
            context.set(map);
        }
        public static void put(String key,Object value) {
            getContext().put(key, value);
        }
    }
}
