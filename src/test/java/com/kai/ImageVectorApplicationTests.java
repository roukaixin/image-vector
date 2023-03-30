package com.kai;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ImageVectorApplication.class)
@Slf4j
class ImageVectorApplicationTests {

    private final StringEncryptor stringEncryptor;

    @Autowired
    ImageVectorApplicationTests(StringEncryptor stringEncryptor) {
        this.stringEncryptor = stringEncryptor;
    }

    @Test
    void contextLoads() {
        // 加密
        String encrypt = stringEncryptor.encrypt("123456");
        log.info(encrypt);
    }

}
