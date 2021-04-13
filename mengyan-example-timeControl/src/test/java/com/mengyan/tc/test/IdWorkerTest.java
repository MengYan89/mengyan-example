package com.mengyan.tc.test;

import com.mengyan.tc.util.IdWorker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 雪花算法生成ID测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IdWorkerTest {

    @Autowired
    private IdWorker idWorker;

    @Test
    public void nextId() {
        for (int i = 0; i < 30; i++) {
            System.out.println(idWorker.nextId());
        }
    }
}
