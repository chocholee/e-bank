import cn.cloudwalk.ebank.core.support.utils.CustomWeiXinXMLProcessUtil;
import com.alibaba.fastjson.annotation.JSONField;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liwenhe on 2016/10/10.
 *
 * @author 李文禾
 */
public class TestCustomWeiXinXMLProcessUtil {

    @Test
    public void TestParseXML() throws Exception {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<xml>\n" +
                "  <name><![CDATA[liwenhe]]></name>\n" +
                "  <age><![CDATA[21]]></age>\n" +
                "  <sex><![CDATA[true]]></sex>\n" +
                "  <hobby type=\"java.util.ArrayList\">\n" +
                "    <value><![CDATA[music]]></value>\n" +
                "    <value><![CDATA[movie]]></value>\n" +
                "    <value><![CDATA[run]]></value>\n" +
                "  </hobby>\n" +
                "  <child>\n" +
                "    <name><![CDATA[liwenhe1]]></name>\n" +
                "    <age><![CDATA[21]]></age>\n" +
                "    <sex><![CDATA[true]]></sex>\n" +
                "  </child>\n" +
                "</xml>";

        ByteArrayInputStream bais = new ByteArrayInputStream(xml.getBytes());
        Map<String, Object> map = CustomWeiXinXMLProcessUtil.parseXML(bais);
        Assert.assertNotNull(map);
    }

    @Test
    public void TestParseObject() throws Exception {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<xml>\n" +
                "  <name><![CDATA[liwenhe]]></name>\n" +
                "  <age><![CDATA[21]]></age>\n" +
                "  <sex><![CDATA[true]]></sex>\n" +
                "  <hobby type=\"java.util.ArrayList\">\n" +
                "    <value><![CDATA[music]]></value>\n" +
                "    <value><![CDATA[movie]]></value>\n" +
                "    <value><![CDATA[run]]></value>\n" +
                "  </hobby>\n" +
                "  <child>\n" +
                "    <name><![CDATA[liwenhe1]]></name>\n" +
                "    <age><![CDATA[21]]></age>\n" +
                "    <sex><![CDATA[true]]></sex>\n" +
                "  </child>\n" +
                "</xml>";

        ByteArrayInputStream bais = new ByteArrayInputStream(xml.getBytes());
        TestOBJ t = CustomWeiXinXMLProcessUtil.parseObject(TestOBJ.class, bais);
        Assert.assertNotNull(t);
    }

    @Test
    public void TestToXML() {
        TestOBJ t = new TestOBJ();
        t.setName("liwenhe");
        t.setAge(21);
        t.setSex(true);

        List<String> hobby = new ArrayList<>();
        hobby.add("music");
        hobby.add("movie");
        hobby.add("run");
        t.setHobby(hobby);

        TestOBJ child = new TestOBJ();
        child.setName("liwenhe1");
        child.setAge(21);
        child.setSex(true);
        t.setChild(child);

        String s = CustomWeiXinXMLProcessUtil.toXML(t);
        Assert.assertNotNull(s);
        System.out.println(s);
    }

}

class TestOBJ {
    @JSONField(ordinal = 1)
    private String name;
    @JSONField(ordinal = 2)
    private Integer age;
    @JSONField(ordinal = 3)
    private boolean sex;
    @JSONField(ordinal = 4)
    private List<String> hobby;
    @JSONField(ordinal = 5)
    private TestOBJ child;
    @JSONField(ordinal = 6)

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

    public List<String> getHobby() {
        return hobby;
    }

    public void setHobby(List<String> hobby) {
        this.hobby = hobby;
    }

    public TestOBJ getChild() {
        return child;
    }

    public void setChild(TestOBJ child) {
        this.child = child;
    }
}
