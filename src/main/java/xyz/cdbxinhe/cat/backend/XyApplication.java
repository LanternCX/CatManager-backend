package xyz.cdbxinhe.cat.backend;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * 程序主入口
 * package xyz.cdbxinhe.cat.backend
 * project backend
 * Created by @author CaoXin on date 2023/03/01
 */
@SpringBootApplication
public class XyApplication implements DisposableBean {
    public static Path CatLiveTempPath;
    /**
     * 入口方法
     *
     * @param args 启动参数 没啥用(什
     */
    public static void main(String[] args) throws IOException {
        // 启动SpringBoot后端
        SpringApplication.run(XyApplication.class, args);
        XyApplication.CatLiveTempPath = Files.createTempDirectory("Cat");
    }

    /**
     * 退出前事务处理
     */
    @Override
    public void destroy() {
    }
}
