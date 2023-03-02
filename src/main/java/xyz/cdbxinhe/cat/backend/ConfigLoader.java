package xyz.cdbxinhe.cat.backend;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 注入式配置读取
 * package xyz.cdbxinhe.cat.backend
 * project backend
 * Created by @author CaoXin on date 2023/03/01
 */
@Data
@Component
@ConfigurationProperties(prefix = "cat")
public class ConfigLoader {
    /**
     * 图片存储根目录
     */
    private String imageRoot;
    /**
     * 默认海报地址
     */
    private String defaultPoster;
}
