package com.hiekn.demo.test.frame.poi;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.StyleSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hiekn.demo.test.TestBase;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelDemo extends TestBase {

    @Resource
    private TransportClient client;

    private static final String EXCEL_FILE_PATH = "F:\\interface.xlsx";

    @Test
    public void poi() throws Exception {
        List<Row> rowList = new ArrayList<>();
        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(EXCEL_FILE_PATH));
        for (int t = 0; t < wb.getNumberOfSheets(); t++) {
            Sheet sheet = wb.getSheetAt(t);
            int lastRowNum = sheet.getLastRowNum();
            // 循环读取
            for (int i = 0; i <= lastRowNum; i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    rowList.add(row);
                    System.out.println("第" + (i + 1) + "行：");
                    // 获取每一单元格的值
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        String value = getCellValue(row.getCell(j));
                        System.out.print(value + " | ");
                    }
                    System.out.println();
                }
            }
        }
        wb.close();
    }

    @Test
    public void esDataToExcel() {

        Map<String, String> map = Maps.newHashMap();
        map.put("01de0a69b40f4e4d88da431f2aa25912", "std_news_test001");
        map.put("a754d266f1ed496ab1890541eb2087c4", "std_achievement_test001");
        map.put("std_news2_test001_data_1539777247040", "std_news2_test001");
        map.put("2d5f9e950e2f49a6a76d58caef6d7055", "std_project_test001");
        map.put("684dffa2793d4512bb04ac2aa2bf5952", "std_standard_test001");
        map.put("75944973d09d420b838c0b3d03f143a4", "std_policy_test001");
        map.put("24fbb5810a5b4887a375f1e61cee50eb", "std_patent_test001");
        map.put("815935c705804a709355a15c7208b9f9", "std_plan_test001");
        map.put("52fcfe3ff4d84a47b16efe3d90ca0797", "std_people_test001");
        map.put("c8fc9ad1fc794563a252be5e20d63afd", "std_company_test001");
        map.put("3f5587416fa3428ab44b68ae9cd98bc2", "std_product_test001");

        ExcelWriter writer = ExcelUtil.getWriter("d:/zh.xlsx");

        map.forEach((k, v) -> {
            List<List<String>> rows = Lists.newArrayList();
            SearchRequestBuilder searchRequestBuilder = client.prepareSearch(k).setTypes(v).setTimeout(TimeValue.timeValueSeconds(60));
            AggregationBuilder by_key = AggregationBuilders.terms("by_annotation_name").field("annotation_tag.name.keyword").size(Integer.MAX_VALUE);
            SearchResponse response = searchRequestBuilder
                    .addAggregation(by_key)
                    .setSize(0)
                    .get();
            Terms by_annotation_name = response.getAggregations().get("by_annotation_name");
            rows.add(Lists.newArrayList("", k));
            long sum = 0;
            for (Terms.Bucket entry : by_annotation_name.getBuckets()) {
                sum += entry.getDocCount();
                rows.add(Lists.newArrayList(entry.getKey().toString(), entry.getDocCount() + ""));
            }
            rows.add(Lists.newArrayList("", ""));
            rows.add(Lists.newArrayList("标引数据合计", sum + ""));
            rows.add(Lists.newArrayList("合计", response.getHits().getTotalHits() + ""));
            writer.passRows(2);
            writer.write(rows);
        });
        writer.close();
    }

    @Test
    public void read() {
        String path = "C:\\Users\\DH\\Desktop\\quick\\kgms_new.xlsx";
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

    @Test
    public void write() {

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

    @Test
    public void nerExcel() throws IOException {
        File dir = new File("G:\\res1");

        List<List<String>> rows = Lists.newArrayList();
        rows.add(Lists.newArrayList("标题", "地名", "人名"));
        for (File file : dir.listFiles()) {
            try {
                List<String> data = Files.readAllLines(Paths.get(file.getAbsolutePath()));
                List<String> row = Lists.newArrayList(file.getName().replace(".txt",""));
                for (String d : data) {
                    if(d.startsWith("人名")){
                        row.add(d.split(":")[1]);
                    }else if(d.startsWith("地名")){
                        row.add(d.split(":")[1]);
                    }
                }
                rows.add(row);
            } catch (Exception e) {
                System.out.println(file.getName());
            }
        }

        ExcelWriter writer = ExcelUtil.getWriter("d:/writeTest.xlsx");
        writer.write(rows);
        writer.close();

    }


    private String getCellValue(Cell cell) {
        Object result = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case STRING:
                    result = cell.getStringCellValue();
                    break;
                case NUMERIC:
//                    result = cell.getNumericCellValue();
                    result = new DecimalFormat("#").format(cell.getNumericCellValue());
                    break;
                case BOOLEAN:
                    result = cell.getBooleanCellValue();
                    break;
                case FORMULA:
                    result = cell.getCellFormula();
                    break;
                case ERROR:
                    result = cell.getErrorCellValue();
                    break;
                case BLANK:
                    break;
                default:
                    break;
            }
        }
        return result.toString();
    }

}
