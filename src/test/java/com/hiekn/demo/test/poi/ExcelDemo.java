package com.hiekn.demo.test.poi;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.StyleSet;
import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.util.List;
import java.util.Map;

public class ExcelDemo {

    public static void main(String[] args) {

        String path = "C:\\Users\\dh1989\\Desktop\\quick\\me\\server.xlsx";

        read(path);

        write();


    }

    public static void read(String path){
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file(path));
        //通过sheet编号获取
//        reader = ExcelUtil.getReader(FileUtil.file(path), 0);
        //通过sheet名获取
//        reader = ExcelUtil.getReader(FileUtil.file(path), "computer");
        List<List<Object>> readAll = reader.read();
        System.out.println(readAll);

        List<Map<String, Object>> maps = reader.readAll();
        System.out.println(maps);

        //读取大量数据
        ExcelUtil.readBySax(path, 0, (sheetIndex, rowIndex, rowList) -> Console.log("[{}] [{}] {}", sheetIndex, rowIndex, rowList));

    }

    public static void write(){

        List<String> row1 = Lists.newArrayList("aa", "bb", "cc", "dd");
        List<String> row2 = Lists.newArrayList("aa1", "bb1", "cc1", "dd1");
        List<String> row3 = Lists.newArrayList("aa2", "bb2", "cc2", "dd2");
        List<String> row4 = Lists.newArrayList("aa3", "bb3", "cc3", "dd3");
        List<String> row5 = Lists.newArrayList("aa4", "bb4", "cc4", "dd4");

        List<List<String>> rows = Lists.newArrayList(row1, row2, row3, row4, row5);

        //通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/writeTest.xlsx");
        //通过构造方法创建writer
        //ExcelWriter writer = new ExcelWriter("d:/writeTest.xls");

        // 定义单元格背景色
        StyleSet style = writer.getStyleSet();
        // 第二个参数表示是否也设置头部单元格背景
        style.setBackgroundColor(IndexedColors.YELLOW, false);

        //设置内容字体
        Font font = writer.createFont();
        font.setBold(true);
        font.setColor(Font.COLOR_RED);
        font.setItalic(true);

        //第二个参数表示是否忽略头部样式
        style.setFont(font, true);

        //跳过当前行，既第一行，非必须，在此演示用
        writer.passCurrentRow();

        //合并单元格后的标题行，使用默认标题样式
        writer.merge(row1.size() - 1, "测试标题");
        //一次性写出内容
        writer.write(rows);
        //关闭writer，释放内存
        writer.close();

    }

}
