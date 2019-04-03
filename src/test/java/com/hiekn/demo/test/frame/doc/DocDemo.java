package com.hiekn.demo.test.frame.doc;

import com.hiekn.demo.test.TestBase;
import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

/**
 * describe about this class
 *
 * @author: DingHao
 * @date: 2019/4/3 17:37
 */
public class DocDemo extends TestBase {


    /**
     * Swagger2Markup 生成adhoc格式文档，可输出为pdf或者html
     * @throws MalformedURLException
     */
    @Test
    public void adhoc() throws MalformedURLException {

        /*Swagger2MarkupConverter.from("json" + "/swagger.json")
                .withPathsGroupedBy(GroupBy.TAGS)// 按tag排序
                .withMarkupLanguage(MarkupLanguage.ASCIIDOC)// 格式
                .build()
                .intoFolder("doc");*/
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withPathsGroupedBy(GroupBy.TAGS)
                .withMarkupLanguage(MarkupLanguage.ASCIIDOC)
                .withOutputLanguage(Language.ZH)
                .withBasePathPrefix()
                .withGeneratedExamples()
                .build();

        Swagger2MarkupConverter.from(new URL("http://192.168.1.119:8888/api/swagger.json"))
                .withConfig(config)
                .build()
                .toFolder(Paths.get("src/docs/generated"));
    }
}
