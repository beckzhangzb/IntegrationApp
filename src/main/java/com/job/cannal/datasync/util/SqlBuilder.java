package com.job.cannal.datasync.util;

import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.canal.protocol.CanalEntry.RowChange;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import com.alibaba.otter.canal.protocol.FlatMessage;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by haha on 2020/8/24.
 */
public class SqlBuilder {

    public static void main(String[] args) {
        String s = new String("'暂无可用车12'");
        System.out.print(s.replaceAll("'", "\\\\'"));
        System.out.print("431\\41");

    }

    public static String buildInsertSql(FlatMessage message) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into ").append(message.getTable()).append("(");
        Set<String> keys = message.getMysqlType().keySet();
        keys.forEach(key -> sql.append("`").append(key).append("`").append(","));
        sql.deleteCharAt(sql.length() - 1);
        sql.append(") values");
        message.getData().forEach(d -> {
            Collection<String> values = d.values();
            sql.append("(");
            values.forEach(value -> {
                if (value == null || value.matches("[0-9]+")) {
                    sql.append(value);
                } else {
                    value = value.indexOf("'") > -1 ? value.replaceAll("'", "\\\\'") : value;
                    sql.append("'").append(value).append("'");
                }
                sql.append(",");
            });
            sql.deleteCharAt(sql.length() - 1);
            sql.append("),");
        });
        sql.deleteCharAt(sql.length() - 1).append("");
        return sql.toString();
    }

    public static List<String> buildUpdateSql(FlatMessage message) {
        List<String> updateSqls = new ArrayList<>();
        List<Map<String, String>> data = message.getData();
        List<Map<String, String>> old = message.getOld();
        for (int i = 0; i < data.size(); i++) {
            Map<String, String> d = data.get(i);
            Map<String, String> o = old.get(i);
            Set<String> updatedColumns = o.keySet();
            StringBuilder sql = new StringBuilder();
            sql.append("update ").append(message.getTable()).append(" set ");
            Iterator<Map.Entry<String, String>> it = d.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();
                if (updatedColumns.contains(entry.getKey())) {
                    String value = entry.getValue();
                    sql.append("`").append(entry.getKey()).append("`");
                    if (value == null || value.matches("[0-9]+")) {
                        sql.append("=").append(value);
                    } else {
                        value = value.indexOf("'") > -1 ? value.replaceAll("'", "\\\\'") : value;
                        sql.append("='").append(value).append("'");
                    }
                    sql.append(",");
                }
            }
            sql.deleteCharAt(sql.length() - 1);
            sql.append(" where ID = ");
            String id = (o.get("ID") == null ? d.get("ID") : o.get("ID"));
            if (StringUtils.isEmpty(id)) {
                id = (o.get("id") == null ? d.get("id") : o.get("id"));
            }
            sql.append(id);
            updateSqls.add(sql.toString());
        }
        return updateSqls;
    }

    public static String buildDeleteSql(FlatMessage message) {
        StringBuilder sql = new StringBuilder("delete from ");
        sql.append(message.getTable());
        sql.append(" where ID in (");
        List<Map<String, String>> data = message.getData();
        data.forEach(d -> {
            String id = d.get("ID");
            sql.append(StringUtils.isEmpty(id) ? d.get("id") : id).append(",");
        });
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
        return sql.toString();
    }

    public static List<String> buildUpdateSql(Entry entry, RowChange rowChange) {
        List<String> updateSqls = new ArrayList<>();
        List<RowData> rowDatasList = rowChange.getRowDatasList();
        for (RowData rowData : rowDatasList) {
            List<Column> newColumnList = rowData.getAfterColumnsList();
            StringBuilder sql = new StringBuilder("update " + entry.getHeader().getSchemaName() + "." + entry.getHeader().getTableName() + " set ");
            for (int i = 0; i < newColumnList.size(); i++) {
                Column column = newColumnList.get(i);
                if (column.getUpdated()) {
                    sql.append(" " + column.getName() + " = '" + column.getValue() + "'").append(",");
                }
            }
            sql.deleteCharAt(sql.length() - 1);
            sql.append(" where ");
            List<Column> oldColumnList = rowData.getBeforeColumnsList();
            for (Column column : oldColumnList) {
                if (column.getIsKey()) {
                    sql.append(column.getName() + "=" + column.getValue());
                    break;
                }
            }
            updateSqls.add(sql.toString());
        }
        return updateSqls;
    }

    public static List<String> buildInsertSql(Entry entry, RowChange rowChange) {
        List<String> insertSqls = new ArrayList<>();
        List<RowData> rowDatasList = rowChange.getRowDatasList();
        for (RowData rowData : rowDatasList) {
            List<Column> columnList = rowData.getAfterColumnsList();
            StringBuilder sql = new StringBuilder("insert into " + entry.getHeader().getSchemaName() + "." + entry.getHeader().getTableName() + " (");
            for (int i = 0; i < columnList.size(); i++) {
                sql.append(columnList.get(i).getName()).append(",");
            }
            sql.deleteCharAt(sql.length() - 1);
            sql.append(") VALUES (");
            for (int i = 0; i < columnList.size(); i++) {
                sql.append("'" + columnList.get(i).getValue() + "'").append(",");
            }
            sql.deleteCharAt(sql.length() - 1);
            sql.append(")");
            insertSqls.add(sql.toString());
        }
        return insertSqls;
    }

    public static List<String> buildDeleteSql(Entry entry, RowChange rowChange) {
        List<String> deleteSqls = new ArrayList<>();
        List<RowData> rowDatasList = rowChange.getRowDatasList();
        for (RowData rowData : rowDatasList) {
            List<Column> columnList = rowData.getBeforeColumnsList();
            StringBuilder sql = new StringBuilder("delete from " + entry.getHeader().getSchemaName() + "." + entry.getHeader().getTableName() + " where ");
            for (Column column : columnList) {
                if (column.getIsKey()) {
                    sql.append(column.getName() + "=" + column.getValue());
                    break;
                }
            }
            deleteSqls.add(sql.toString());
        }
        return deleteSqls;
    }
}
