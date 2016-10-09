package cn.cloudwalk.ebank.core.support.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liwenhe on 2016/10/9.
 *
 * @author 李文禾
 */
public class WeiXinXMLProcessUtil {

    public static Map<String, String> parseXML(InputStream in) throws Exception {
        try {
            Map<String, String> map = new HashMap<>();
            SAXReader reader = new SAXReader();
            Document document = reader.read(in);
            Element root = document.getRootElement();
            List<Element> elementList = root.elements();
            if (!elementList.isEmpty()) {
                for (Element e : elementList) {
                    map.put(e.getName(), e.getText());
                }
            }
            return map;
        } finally {
            in.close();
        }
    }

    public static <T> T parseObject(Class<T> type, InputStream in) throws Exception {
        Map<String, String> map = parseXML(in);
        String text = JSON.toJSONString(map);
        return JSONObject.parseObject(text, type);
    }

    public static String toXML(Object object) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("xml");

        String text = JSON.toJSONString(object);
        JSONObject json = JSONObject.parseObject(text, Feature.OrderedField);
        for (String key : json.keySet()) {
            Element element = root.addElement(key);
            element.addCDATA(json.getString(key));
        }

        return document.asXML();
    }

    public static void main(String[] args) throws Exception {
        String text = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<xml><age><![CDATA[12]]></age><name><![CDATA[liwenhe]]></name><sex><![CDATA[true]]></sex></xml>";
        Test t = parseObject(Test.class, new StringBufferInputStream(text));
        System.out.println();
    }

    static class Test {
        private String name;
        private Integer age;
        private boolean sex;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public boolean isSex() {
            return sex;
        }

        public void setSex(boolean sex) {
            this.sex = sex;
        }
    }

}
