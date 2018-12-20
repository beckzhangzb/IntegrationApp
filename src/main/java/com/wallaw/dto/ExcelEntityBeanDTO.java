package com.wallaw.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @since 2018-8-21 16:58:36
 */
public class ExcelEntityBeanDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String DEFAULT_CHARSET      = "UTF-8";
    public static final String DEFAULT_CONTENT_TYPE = "application/msexcel";

    /**导出文件名*/
    private String fileName;

    /**表头*/
    private String[] headers;

    /**数据*/
    private List<String[]> contents;

    /**excle表最开始添加一合并行数据*/
    private String firstInsertRow;

    public String getFirstInsertRow() {
        return firstInsertRow;
    }

    public void setFirstInsertRow(String firstInsertRow) {
        this.firstInsertRow = firstInsertRow;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String[] getHeaders() {
        return headers;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    public List<String[]> getContents() {
        return contents;
    }

    public void setContents(List<String[]> contents) {
        this.contents = contents;
    }

}
