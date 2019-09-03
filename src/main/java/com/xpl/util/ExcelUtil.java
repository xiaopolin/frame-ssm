package com.xpl.util;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ExcelUtil {

    /**
     * 内置实体类以操作多sheet的Excel
     * */
    public static class ExcelParam{
        public ExcelParam(List<? extends BaseRowModel> data, Sheet sheet){
            this.data = data;
            this.sheet = sheet;
        }
        private List<? extends BaseRowModel> data;
        private Sheet sheet;
        private List<? extends BaseRowModel> getData() { return data; }
        private Sheet getSheet() { return sheet; }
    }

    /**
     * 导出简单的Excel
     * */
    public static void exportExcel(List<? extends BaseRowModel> data, String sheetName, String filePath) throws IOException {
        OutputStream outputStream = new FileOutputStream(filePath);
        ExcelWriter excelWriter = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX);

        Sheet sheet = new Sheet(1, 0, data.get(0).getClass());
        sheet.setSheetName(null != sheetName && !sheetName.equals("") ? sheetName : "Excel数据");

        excelWriter.write(data, sheet);

        excelWriter.finish();
        outputStream.close();
    }

    /**
     * 导出多Sheet的Excel
     * */
    public static void exportExcelS(List<ExcelParam> excelParams, String filePath) throws IOException {
        OutputStream outputStream = new FileOutputStream(filePath);
        ExcelWriter excelWriter = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX);

        if (null == excelParams){
            return;
        }

        for (ExcelParam excelParam : excelParams){
            excelWriter.write(excelParam.getData(), excelParam.getSheet());
        }

        excelWriter.finish();
        outputStream.close();
    }

    /**
     * 导出多Sheet的Excel
     * */
    public static void exportWebExcelS(List<ExcelParam> excelParams, HttpServletResponse response){
        ServletOutputStream sos = null;
        try {
            sos = response.getOutputStream();
        }catch (IOException e) {
            e.printStackTrace();
            return;
        }


        ExcelWriter excelWriter = new ExcelWriter(sos, ExcelTypeEnum.XLSX);

        if (null == excelParams){
            return;
        }

        for (ExcelParam excelParam : excelParams){
            excelWriter.write(excelParam.getData(), excelParam.getSheet());
        }
        response.setHeader("Content-disposition", "attachment;filename=活动记录.xlsx");
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");

        excelWriter.finish();
        try {
            sos.flush();
            sos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
