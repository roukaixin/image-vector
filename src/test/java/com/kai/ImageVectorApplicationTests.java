package com.kai;

import com.kai.utlis.GiteeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ImageVectorApplicationTests {

    @Test
    void contextLoads() {
        String newFile = GiteeUtil.createNewFile(null, null, null);

    }

}
