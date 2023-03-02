package com.chat;

import com.chat.DemoApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 *
 * @author luan.tomiozzo
 */
public class ServletInitializr extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        System.out.println("\n------------------------------------------------- INICIANDO API-CHAT ------------------------------------------------- \n");
        return application.sources(DemoApplication.class);
    }
}
