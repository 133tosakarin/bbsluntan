package com.dc.bbsluntan;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class BbsluntanApplication {

    public static void main(String[] args) {
        log.info("项目启动");
        SpringApplication.run(BbsluntanApplication.class, args);
    }

}
