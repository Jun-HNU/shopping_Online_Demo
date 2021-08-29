package com.hnu.websocket.client;


//import com.websocket.javaclient.JavaClient;
import org.java_websocket.client.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/jc")
public class JavaClientController {

    @Autowired
    private JavaClient javaClient;

    @GetMapping(value = "hello")
    public void hello(String str){

        WebSocketClient client = this.javaClient.getClient("ws://localhost:8085/ws/server");

        if (client != null){

            client.send(str);

        }
        
    }
}
