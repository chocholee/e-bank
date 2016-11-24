import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.testng.annotations.Test;

import java.util.Date;

/**
 * Created by liwenhe on 2016/9/20.
 *
 * @author 李文禾
 */
public class TestMd5 {

    @Test
    public void TestMD5() {
        System.out.println(new Date().getTime());
        Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
        System.out.println(md5PasswordEncoder.encodePassword("123", "liwenhe"));
    }

}
