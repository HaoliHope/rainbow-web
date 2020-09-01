package org.test;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rainbow.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class AppTest {

    @Autowired
    private StringEncryptor encode;

    @Test
    public void test1(){
        System.out.println(encode.encrypt("jdbc:mysql://175.24.11.117:3306/springboot_cli?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8"));
        System.out.println("encode.encrypt(\"root\") = " + encode.encrypt("root"));
        System.out.println("encode.encrypt(\"lh@686...\") = " + encode.encrypt("lh@686..."));
        System.out.println("encode.encrypt(\"175.24.11.117\") = " + encode.encrypt("175.24.11.117"));
        System.out.println("encode.encrypt(\"minioadmin\") = " + encode.encrypt("minioadmin"));

    }
}
