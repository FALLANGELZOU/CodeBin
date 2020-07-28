package com.angel.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Author: Angel_zou
 * @Date: Created in 10:45 2020/7/13
 * @Connection: ahacgn@gmail.com
 * @Description: websocket的配置类，用来启动websocket服务
 */

@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}


//之后使用 `@ServerEndpoint("/***")` 标注在类上就可以将该类修改为 websocket 服务类。
//
//        四个主要方法：
//
//        `@OnOpen` 与客户端建立连接时触发
//
//        `@OnClose` 与客户端断开连接时触发
//
//        `@OnMessage` 接收到客户端的消息时触发
//
//        `OnError` 发生错误时触发
//
//        （将四个注解分别标注在方法上）
