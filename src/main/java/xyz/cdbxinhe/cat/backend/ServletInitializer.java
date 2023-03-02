package xyz.cdbxinhe.cat.backend;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 服务初始化
 * package xyz.cdbxinhe.cat.backend
 * project backend
 * Created by @author CaoXin on date 2023/03/01
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(XyApplication.class);
    }
}
