import org.testng.annotations.Test;

import java.lang.reflect.ParameterizedType;

/**
 * Created by liwenhe on 2016/9/21.
 *
 * @author 李文禾
 */
public class TestGenericType {

    @Test
    public void TestGenericType() {
        B b = new B();
        ParameterizedType parameterizedType = (ParameterizedType)b.getClass().getGenericSuperclass();
        System.out.println(parameterizedType.getActualTypeArguments().length);
        System.out.println(parameterizedType.getActualTypeArguments()[0]);
    }

}

class A<T, ID> {

}

class B extends A<String, String> {

}
