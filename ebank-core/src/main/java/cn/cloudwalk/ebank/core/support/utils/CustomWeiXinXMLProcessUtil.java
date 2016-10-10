package cn.cloudwalk.ebank.core.support.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.util.TypeUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by liwenhe on 2016/10/9.
 *
 * @author 李文禾
 */
public class CustomWeiXinXMLProcessUtil {

    /**
     * 解析xml数据并生成Map对象
     *
     * @param in xml流
     * @return Map
     * @throws Exception
     */
    public static Map<String, Object> parseXML(InputStream in) throws Exception {
        try {
            // 构造map对象
            Map<String, Object> map = new HashMap<>();

            // 构造xml sax reader
            SAXReader reader = new SAXReader();

            // 读入流生成document并解析element元素
            Document document = reader.read(in);
            Element root = document.getRootElement();
            List<Element> elementList = root.elements();
            if (!elementList.isEmpty()) {
                for (Element e : elementList) {
                    iteratorElement(e, map);
                }
            }

            return map;
        } finally {
            in.close();
        }
    }

    /**
     * xml生成相应java bean对象
     *
     * @param type java bean对象的class
     * @param in   xml流
     * @return 返回相应的java bean对象
     * @throws Exception
     */
    public static <T> T parseObject(Class<T> type, InputStream in) throws Exception {
        Map<String, Object> map = parseXML(in);
        String text = JSON.toJSONString(map);
        return JSONObject.parseObject(text, type, Feature.IgnoreNotMatch);
    }

    /**
     * java bean生成xml
     *
     * @param object java bean对象
     * @return String
     */
    public static String toXML(Object object) {
        // 创建document并指定根节点
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("xml");

        // java bean转换为json并生成element
        String text = JSON.toJSONString(object);
        JSONObject json = JSONObject.parseObject(text, Feature.OrderedField);
        for (String key : json.keySet()) {
            Element element = root.addElement(key);
            iteratorJSON(element, json.get(key));
        }

        // 转换为string
        return document.asXML();
    }

    /**
     * 迭代element元素
     *
     * @param e
     * @param list
     */
    private static void iteratorElement(Element e, List list) {
        if (e.elements().isEmpty()) {
            list.add(e.getText());
        } else {
            Map<String, Object> childMap = new HashMap<>();
            List<Element> elementList = e.elements();
            for (Element e1 : elementList) {
                iteratorElement(e1, childMap);
            }
            list.add(childMap);
        }
    }

    /**
     * 迭代element元素
     *
     * @param e
     * @param map
     */
    private static void iteratorElement(Element e, Map<String, Object> map) {
        if (e.elements().isEmpty()) {
            map.put(e.getName(), e.getText());
        } else {
            List<Element> elementList = e.elements();
            if (null != e.attribute("type") && e.attribute("type").getValue().equals(ArrayList.class.getName())) {
                List list = new ArrayList();
                for (Element e1 : elementList) {
                    iteratorElement(e1, list);
                }
                map.put(e.getName(), list);
            } else {
                Map<String, Object> childMap = new HashMap<>();
                for (Element e1 : elementList) {
                    iteratorElement(e1, childMap);
                }
                map.put(e.getName(), childMap);
            }
        }
    }

    /**
     * 迭代json对象并生成element
     *
     * @param element
     * @param o
     */
    private static void iteratorJSON(Element element, Object o) {
        // 迭代JSONObject对象
        if (o instanceof JSONObject) {
            for (String key : ((JSONObject) o).keySet()) {
                Element child = element.addElement(key);
                iteratorJSON(child, ((JSONObject) o).get(key));
            }
        } else if (o instanceof JSONArray) {
            // 迭代JSONArray对象
            element.addAttribute("type", ArrayList.class.getName());
            Iterator iterator = ((JSONArray) o).iterator();
            while (iterator.hasNext()) {
                Element child = element.addElement("value");
                Object o1 = iterator.next();
                iteratorJSON(child, o1);
            }
        } else {
            // 解析java类型并转换为String
            Class clazz = o.getClass();
            if (clazz == boolean.class || clazz == Boolean.class) {
                element.addCDATA(TypeUtils.castToBoolean(o).toString());
                return;
            }

            if (clazz == byte.class || clazz == Byte.class) {
                element.addCDATA(TypeUtils.castToByte(o).toString());
                return;
            }

            if (clazz == char.class || clazz == Character.class) {
                element.addCDATA(TypeUtils.castToChar(o).toString());
                return;
            }

            if (clazz == short.class || clazz == Short.class) {
                element.addCDATA(TypeUtils.castToShort(o).toString());
                return;
            }

            if (clazz == int.class || clazz == Integer.class) {
                element.addCDATA(TypeUtils.castToInt(o).toString());
                return;
            }

            if (clazz == long.class || clazz == Long.class) {
                element.addCDATA(TypeUtils.castToLong(o).toString());
                return;
            }

            if (clazz == float.class || clazz == Float.class) {
                element.addCDATA(TypeUtils.castToFloat(o).toString());
                return;
            }

            if (clazz == double.class || clazz == Double.class) {
                element.addCDATA(TypeUtils.castToDouble(o).toString());
                return;
            }

            if (clazz == String.class) {
                element.addCDATA(TypeUtils.castToString(o));
                return;
            }

            if (clazz == BigDecimal.class) {
                element.addCDATA(TypeUtils.castToBigDecimal(o).toString());
                return;
            }

            if (clazz == BigInteger.class) {
                element.addCDATA(TypeUtils.castToBigInteger(o).toString());
                return;
            }

            if (clazz == Date.class) {
                element.addCDATA(TypeUtils.castToDate(o).toString());
                return;
            }

            if (clazz == java.sql.Date.class) {
                element.addCDATA(TypeUtils.castToSqlDate(o).toString());
                return;
            }

            if (clazz == java.sql.Timestamp.class) {
                element.addCDATA(TypeUtils.castToTimestamp(o).toString());
                return;
            }

            if (clazz.isEnum()) {
                element.addCDATA(((Enum) o).name());
            }
        }
    }
}
