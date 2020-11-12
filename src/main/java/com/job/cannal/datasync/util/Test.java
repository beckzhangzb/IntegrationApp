package com.job.cannal.datasync.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by haha on 2020/8/27.
 */
//@Component
public class Test implements ApplicationContextAware {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        try {
            DatabaseMetaData metaData = jdbcTemplate.getDataSource().getConnection().getMetaData();
            ResultSet table = metaData.getTables(null, "root", null, new String[]{"TABLE"});
            while (table.next()) {
                String tableName = table.getString("TABLE_NAME");
                //System.out.println("public static final String " + tableName.toUpperCase() + " = \"" + tableName + "\";");
                //writeFile(tableName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //writeFile("order_info");
    }

    public void writeFile(String tableName) {
        File file = new File("C:/Users/haha/Desktop/javafiles/" + underlineToHump(tableName) + "Handler.java");
        try {
            if (!file.exists()) {
                file.createNewFile();
            } else {
                file.delete();
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("package com.smcx.datasync.handler;\n");
            bw.write("import com.smcx.datasync.annotation.TableHandler;\n");
            bw.write("import com.smcx.datasync.handler.AbstractHandler;\n");
            bw.write("import com.smcx.datasync.util.Table;\n");
            bw.write("import org.springframework.stereotype.Component;\n\n");
            bw.write("/**\n");
            bw.write(" * Created by haha on 2020/8/21.\n");
            bw.write(" */\n");
            bw.write("@Component\n");
            bw.write("@TableHandler(table = Table." + tableName.toUpperCase() + ")\n");
            bw.write("public class " + underlineToHump(tableName) + "Handler extends AbstractHandler {\n");
            bw.write("}");
            bw.flush();
            bw.close();
            fw.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private final static String UNDERLINE = "_";

    public static String underlineToHump(String para) {
        StringBuilder result = new StringBuilder();
        String a[] = para.split(UNDERLINE);
        for (String s : a) {
            if (!para.contains(UNDERLINE)) {
                result.append(s);
                continue;
            }

            result.append(s.substring(0, 1).toUpperCase());
            result.append(s.substring(1).toLowerCase());

        }
        return result.toString();
    }
}
