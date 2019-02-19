package com.hiekn.demo.test.frame.xml;

import com.google.gson.JsonObject;
import com.hiekn.demo.test.TestBase;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;

/**
 * describe about this class
 *
 * @author DingHao
 * @date 2019/2/19 14:37
 */
public class XmlDemo extends TestBase {

    static class MyDataBean {

        private String ip;
        private Integer port;
        private String username;
        private String password;
        private String database;
        private String table;

        private List<MyDataTableCreateBean> fieldMapping;


        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getDatabase() {
            return database;
        }

        public void setDatabase(String database) {
            this.database = database;
        }

        public String getTable() {
            return table;
        }

        public void setTable(String table) {
            this.table = table;
        }

        public List<MyDataTableCreateBean> getFieldMapping() {
            return fieldMapping;
        }

        public void setFieldMapping(List<MyDataTableCreateBean> fieldMapping) {
            this.fieldMapping = fieldMapping;
        }
    }
    static class MyDataTableCreateBean {

        public class Type{
            public final static int Integer = 0;
            public final static int String = 1;
            public final static int Long = 2;
            public final static int Date = 3;
            public final static int Object = 4;
            public final static int Nested= 5;
            public final static int Array= 6;
            public final static int StringArray= 7;
            public final static int Double= 8;
            public final static int Float= 9;
        }

        private String field;
        private int type;
        private int isIndex;

        private JsonObject settings;

        public String getField() {
            return field;
        }
        public void setField(String field) {
            this.field = field;
        }
        public int getType() {
            return type;
        }
        public void setType(int type) {
            this.type = type;
        }
        public int getIsIndex() {
            return isIndex;
        }
        public void setIsIndex(int isIndex) {
            this.isIndex = isIndex;
        }

        public JsonObject getSettings() {
            return settings;
        }

        public void setSettings(JsonObject settings) {
            this.settings = settings;
        }
    }

    @Test
    public void xstream(){


        XStream xStream = new XStream();
        xStream.ignoreUnknownElements();

        xStream.alias("steps", List.class);
        xStream.alias("step", MyDataBean.class);
        xStream.alias("mongo_field", MyDataTableCreateBean.class);

        xStream.aliasField("mongo_host", MyDataBean.class,"ip");
        xStream.aliasField("mongo_port", MyDataBean.class,"port");
        xStream.aliasField("mongo_db", MyDataBean.class,"database");
        xStream.aliasField("mongo_collection", MyDataBean.class,"table");
        xStream.aliasField("mongo_fields", MyDataBean.class,"fieldMapping");
        xStream.aliasField("incoming_field_name", MyDataTableCreateBean.class,"field");

        xStream.setClassLoader(XmlDemo.class.getClassLoader());
        xStream.addPermission(AnyTypePermission.ANY);
        List<MyDataBean> list = (List<MyDataBean>)xStream.fromXML(readXMLAsString("/transformation/step"));

        System.out.println(list);
    }

    public String readXMLAsString(String expression) {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("mysql.ktr");
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(is);
            XPath xpath = XPathFactory.newInstance().newXPath();
            NodeList evaluate = (NodeList)xpath.evaluate(expression, document, XPathConstants.NODESET);
            StringBuilder sb = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?><steps>");
            for (int i = 0; i < evaluate.getLength(); i++) {
                sb.append(toString(evaluate.item(i)).replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>",""));
            }
            return sb.append("</steps>").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String toString(Node node) {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            StringWriter sw = new StringWriter();
            transformer.transform(new DOMSource(node), new StreamResult(sw));
            return sw.toString();
        } catch (TransformerException te) {
            throw new RuntimeException(te.getMessage());
        }
    }

}
