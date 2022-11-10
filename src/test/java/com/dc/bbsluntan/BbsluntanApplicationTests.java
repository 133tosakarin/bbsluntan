package com.dc.bbsluntan;

import com.dc.bbsluntan.entity.UserEntity;
import com.dc.bbsluntan.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BbsluntanApplicationTests {
    @Autowired
    private UserService userService;
    @Test
    void contextLoads() {
    }

    public static void main(String[] args) {
        BbsluntanApplicationTests bbs = new BbsluntanApplicationTests();

    }

}
