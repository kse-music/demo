package com.hiekn.demo.test.java;

import com.hiekn.demo.test.TestBase;
import io.github.robwin.markup.builder.MarkupLanguage;
import io.github.robwin.swagger2markup.GroupBy;
import io.github.robwin.swagger2markup.Swagger2MarkupConverter;
import org.junit.Test;

import java.util.Map;
import java.util.Properties;

public class SystemPropertiesTest extends TestBase {
	
	@Test
	public void getAll(){
		
		 Properties props = System.getProperties();
		 props.list(System.out);

        Map<String, String> envs = System.getenv();
		for (Map.Entry<String, String> env : envs.entrySet()) {
			System.out.println(env.getKey() + " = " + env.getValue());
		}
		
	}

    public static void main(String[] args) throws Exception{
        Swagger2MarkupConverter.from("json" + "/swagger.json")
                .withPathsGroupedBy(GroupBy.TAGS)// 按tag排序
                .withMarkupLanguage(MarkupLanguage.ASCIIDOC)// 格式
                .build()
                .intoFolder("doc");
//        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
//                .withPathsGroupedBy(GroupBy.TAGS)
//                .withMarkupLanguage(MarkupLanguage.ASCIIDOC)
//                .withOutputLanguage(Language.ZH)
//                .build();
//        Swagger2MarkupConverter.from("json" + "/swagger.json").withConfig(config).build();
    }

}
