package xyz.cdbxinhe.cat.backend.util.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import xyz.cdbxinhe.cat.backend.ConfigLoader;

/**
 * 依赖注入工具类
 * 用于让静态方法使用动态注入依赖
 * package xyz.cdbxinhe.cat.backend.util.data
 * project backend
 * Created by @author CaoXin on date 2023/03/05
 */
@Component
public class WireRepo {
    public static ConfigLoader configLoader;
    public static int springPort;

    @Autowired
    public void autoWire(ConfigLoader configLoader) {
        WireRepo.configLoader = configLoader;
    }

    @EventListener(WebServerInitializedEvent.class)
    public void onApplicationEvent(WebServerInitializedEvent evt) {
        WireRepo.springPort = evt.getWebServer().getPort();
    }
}
