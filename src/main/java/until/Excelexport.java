package until;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFont;

import java.util.List;
import java.util.Map;

public class Excelexport {
    public SXSSFWorkbook export_Flight (List<Map> result, String[] headers, String[] fields) {
        SXSSFWorkbook workbook = new SXSSFWorkbook(100);//内存中保留100条数据，以免内存溢出
        Sheet sheet = workbook.createSheet();//获取该工作区间的第一个sheet
        //sheet.autoSizeColumn(1, true);//宽度自适应
        Row row = sheet.createRow(0);
        // 创建表头单元格样式
        CellStyle cellheardStyle = workbook.createCellStyle();
        // 设置单元格字体样式
        XSSFFont font = (XSSFFont) workbook.createFont();
        font.setColor(Font.COLOR_RED);
        font.setFontName("黑体");// 设置字体的样式
        font.setFontHeight(12);// 设置字体的大小
        cellheardStyle.setFont(font);// 将字体填充到表格中去
        // 设置单元格边框为细线条（上下左右）
        cellheardStyle.setBorderLeft(BorderStyle.THIN);
        cellheardStyle.setBorderBottom(BorderStyle.THIN);
        cellheardStyle.setBorderRight(BorderStyle.THIN);
        cellheardStyle.setBorderTop(BorderStyle.THIN);
        cellheardStyle.setAlignment(HorizontalAlignment.CENTER);
        for (int i = 0; i < headers.length; i++) {
            // sheet.createRow(0).createCell(i).setCellValue(headers[i]);

            Cell title = row.createCell(i);
            title.setCellStyle(cellheardStyle);
            title.setCellValue(headers[i]);
        }
        // 创建表格单元格样式
        CellStyle cellbodyStyle = workbook.createCellStyle();
        // 设置单元格字体样式
        XSSFFont fonta = (XSSFFont) workbook.createFont();

        fonta.setFontName("黑体");// 设置字体的样式
        fonta.setFontHeight(10);// 设置字体的大小
        cellbodyStyle.setFont(fonta);// 将字体填充到表格中去
        // 设置单元格边框为细线条（上下左右）
        cellbodyStyle.setBorderLeft(BorderStyle.THIN);
        cellbodyStyle.setBorderBottom(BorderStyle.THIN);
        cellbodyStyle.setBorderRight(BorderStyle.THIN);
        cellbodyStyle.setBorderTop(BorderStyle.THIN);
        cellbodyStyle.setAlignment(HorizontalAlignment.CENTER);//设置单元格内容为居中
        for (int i = 0; i < result.size(); i++) {
            Row row1 = sheet.createRow(i + 1);

            for (int j = 0; j < fields.length; j++) {
                Cell title1 =row1.createCell(j);
                title1.setCellStyle(cellbodyStyle);
                title1.setCellValue(String.valueOf(result.get(i).get(fields[j])));
            }
        }

        return workbook;
    }
}
