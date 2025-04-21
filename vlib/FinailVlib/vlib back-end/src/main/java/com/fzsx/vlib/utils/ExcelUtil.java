package com.fzsx.vlib.utils;

import javax.servlet.http.HttpServletResponse;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * vlib - ExcelUtil
 * TODO
 *
 * @author HeiYunxi 黑昀熙
 * @version 1.0
 */
public class ExcelUtil {
    public static void outputExcel(HttpServletResponse response, List list, Class cla, String sheetName) throws IOException {
        //返回类型
        response.setContentType("application/vnd.ms-excel");
        String fileName = URLEncoder.encode(sheetName,"UTF-8");
        response.setHeader("Content-Dsiposition","attachment;filename="+fileName);
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();
        WriteSheet sheet = EasyExcel.writerSheet(0,sheetName).head(cla).build();
        excelWriter.write(list,sheet);
        excelWriter.finish();
    }

}
