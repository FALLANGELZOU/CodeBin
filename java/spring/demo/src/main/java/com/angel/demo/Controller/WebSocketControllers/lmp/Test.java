package com.angel.demo.Controller.WebSocketControllers.lmp;

import com.angel.demo.Controller.WebSocketControllers.WebSocketController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author: Angel_zou
 * @Date: Created in 11:03 2020/7/13
 * @Connection: ahacgn@gmail.com
 * @Description: websocket的测试类
 */
@ServerEndpoint("/ws/test/{userId}")
@Component
@Slf4j
public class Test implements WebSocketController{

    //  当前连接数
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<Test> webSocketSet = new CopyOnWriteArraySet<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    private String userId;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) throws IOException {
        this.session = session;
        this.userId = userId;
        webSocketSet.add(this);
        log.info("session "+ session +" websocket连接");
        sendMessage("连接成功");
        addOnlineCount();

    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        log.info("session"+session+";   author:"+userId+" websocket连接关闭");
        subOnlineCount();
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param queryType 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String queryType) throws IOException {
        log.info("收到信息:" + queryType);
        sendInfo("hello");
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
    }

    /**
     * 实现服务器主动推送
     */
    private void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 实现服务器主动群发消息
     */
    public static void sendInfo(String message) throws IOException {
        log.info("websocket群发消息，消息："+message);
        for (Test item : webSocketSet) {
            item.sendMessage(message);
        }
    }

    private void addOnlineCount(){
        onlineCount ++;
    }
    private void subOnlineCount(){
        onlineCount --;
    }

}
