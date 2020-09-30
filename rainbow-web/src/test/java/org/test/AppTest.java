package org.test;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rainbow.App;
import org.rainbow.mapper.SysMenuMapper;
import org.rainbow.utils.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class AppTest {

    @Autowired
    private StringEncryptor encode;
    @Autowired
    private TreeUtil treeUtil;
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Test
    public void test1(){
        System.out.println("encode.encrypt(\"123\") = " + encode.encrypt("123"));
        System.out.println("encode.decrypt(\"5yG7mH5qfE5txuqiQ5/Ei6XuD19qNndM66EmIW2yl0raZU1qimpiMssyxr/ZE6ie\") = " + encode.decrypt("5yG7mH5qfE5txuqiQ5/Ei6XuD19qNndM66EmIW2yl0raZU1qimpiMssyxr/ZE6ie"));
        // encode.decrypt()
        // System.out.println(encode.encrypt("jdbc:mysql://175.24.11.117:3306/springboot_cli?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8"));
        // System.out.println("encode.encrypt(\"root\") = " + encode.encrypt("root"));
        // System.out.println("encode.encrypt(\"lh@686...\") = " + encode.encrypt("lh@686..."));
        // System.out.println("encode.encrypt(\"175.24.11.117\") = " + encode.encrypt("175.24.11.117"));
        // System.out.println("encode.encrypt(\"minioadmin\") = " + encode.encrypt("minioadmin"));

    }

    @Test
    public void test2(){
        // Set<String> strings = sysMenuMapper.selectMenuByUserId(1L);
        // System.out.println("strings = " + strings);
    }
}
