package com.hiekn.demo.test.java.senior.editor;

import com.hiekn.demo.test.TestBase;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * describe about this class
 *
 * @author DingHao
 * @date 2019/1/4 14:04
 */
public class PropertyEditorDemo extends TestBase {

    @Test
    public void editor() throws Exception {
        Map<String, String> parameters = new HashMap<String, String>(){
            {
                //这里的key要和Node里面的属性名一致
                put("nodeName", "迈克");
                put("userBean", "mike|标题|11|内容|搜索|不搜索|2019-01-04 14:08:00");
            }
        };
        Node convert = PropertyEditorSample.convert(parameters);
        System.out.println(convert.getNodeName());
        System.out.println(convert.getUserBean());
    }

}
