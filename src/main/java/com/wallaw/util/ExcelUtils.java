package com.wallaw.util;

import com.wallaw.dto.ExcelEntityBeanDTO;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * @since 2018-8-21 16:59:05
 */
public class ExcelUtils {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtils.class);

    /**
     * 导出Excel
     *
     * @param entity
     * @return
     */
    public final static void exportExcel(ExcelEntityBeanDTO entity, HttpServletResponse response) {
        // 定义输出类型，设置为浏览器下载类型
        response.setContentType("application/msexcel;charset=utf-8");
        try {
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + new String((entity.getFileName()).getBytes("gbk"), "iso8859-1") + ".xls");
        } catch (UnsupportedEncodingException e1) {
            throw new RuntimeException(e1);
        }
        WritableWorkbook workbook = null;
        try {
            workbook = Workbook.createWorkbook(response.getOutputStream());
            WritableSheet sheet = createSheet(workbook, "Sheet", 0);

            // 写表头
            if (entity.getHeaders() != null && entity.getHeaders().length > 0) {
                WritableCellFormat headerFormat = getHeaderFormat();
                for (int i = 0; i < entity.getHeaders().length; i++) {
                    sheet.setColumnView(i, 30);
                    sheet.addCell(new Label(i, 0, entity.getHeaders()[i], headerFormat));
                }
            }

            // 写数据
            if (entity.getContents() != null && entity.getContents().size() > 0) {
                WritableCellFormat contentFormat = getContentFormat();
                for (int i = 0; i < entity.getContents().size(); i++) {
                    String[] content = entity.getContents().get(i);
                    if (content != null && content.length > 0) {
                        for (int j = 0; j < content.length; j++) {
                            sheet.addCell(new Label(j, (i + 1), content[j], contentFormat));
                        }
                    }

                }
            }

            if (!StringUtils.isEmpty(entity.getFirstInsertRow())) {
                sheet.insertRow(0);
                sheet.mergeCells(0, 0, entity.getHeaders().length - 1, 0);
                WritableCellFormat headerFormat = getHeaderFormat();
                headerFormat.setAlignment(Alignment.LEFT);
                sheet.addCell(new Label(0, 0, entity.getFirstInsertRow(), headerFormat));
            }

            workbook.write();
        } catch (Exception e) {
            logger.error("导出Excel文件失败", e);
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (Exception e) {
                    logger.error("关闭文件失败", e);
                }
            }
        }
    }

    /**
     * 创建数据页，并设置表格属性
     *
     * @param workbook
     * @return
     */
    public static WritableSheet createSheet(WritableWorkbook workbook, String name, int index) {
        WritableSheet sheet = workbook.createSheet(name, index);
        // 设置表格属性
        jxl.SheetSettings sheetset = sheet.getSettings();
        sheetset.setProtected(false);
        return sheet;
    }

    /**
     * 表头样式
     *
     * @return
     * @throws WriteException
     */
    public static WritableCellFormat getHeaderFormat() throws WriteException {
        WritableFont font = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
        WritableCellFormat format = new WritableCellFormat(font);
        format.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
        format.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
        format.setAlignment(Alignment.CENTRE); // 文字水平对齐
        format.setWrap(false); // 文字是否换行
        return format;
    }

    /**
     * 数据样式
     *
     * @return
     * @throws WriteException
     */
    public static WritableCellFormat getContentFormat() throws WriteException {
        WritableFont font = new WritableFont(WritableFont.ARIAL, 10);
        WritableCellFormat format = new WritableCellFormat(font);
        format.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
        format.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
        format.setAlignment(Alignment.LEFT); // 文字水平对齐
        format.setWrap(true); // 文字是否换行
        return format;
    }

}