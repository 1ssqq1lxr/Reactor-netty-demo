package com.iot.device;


import com.alibaba.fastjson.JSON;
import com.iot.device.msi.Content;
import com.iot.device.transport.ConnectionConfig;
import com.iot.device.transport.DuplexConnection;
import com.iot.device.transport.ServerTransport;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.concurrent.CountDownLatch;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws InterruptedException {
                CountDownLatch countDownLatch =new CountDownLatch(1);
        new CountDownLatch(1);
        Flux<Integer> s= Flux.create(integerFluxSink -> {

        });
        s.blockFirst();
        ServerTransport serverTransport =new ServerTransport();
        Flux<DuplexConnection> localhost = serverTransport.start(() -> ConnectionConfig.builder().ip("localhost").port(1884).build());
        localhost.subscribe(duplexConnection -> {
            duplexConnection.getInbound().receiveObject().cast(Content.class);
            duplexConnection.getInbound().receiveObject().subscribe(content->{
//                content.
            });
        });

//        Float[] shorts =new Float[2];
//        shorts[0] =(float) 1.1111111111;
//        shorts[1]=(float) 2.222222222222;
//        ConnectionConfig connectionConfi=  ConnectionConfig.builder().ip("localhost").port(1884).build();
//        String jsonString=JSON.toJSONString(connectionConfi);
//        Object object=JSON.parseObject(jsonString);
//        System.out.println(jsonString);
        countDownLatch.await();
    }
}
