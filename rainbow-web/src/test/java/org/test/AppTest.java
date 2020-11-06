package org.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rainbow.App;
import org.rainbow.mapper.system.SysMenuMapper;
import org.rainbow.utils.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class AppTest {

    @Autowired
    private TreeUtil treeUtil;
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Test
    public void test1() {

    }

    @Test
    public void test2() {

    }
}
