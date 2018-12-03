package com.iot.device.connfig;

import com.iot.device.msi.Content;
import com.iot.device.msi.Type;
import com.iot.device.respository.WaterRespository;
import com.iot.device.transport.ConnectionConfig;
import com.iot.device.transport.ServerTransport;
import com.iot.device.transport.Transport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

/**
 * @Auther: lxr
 * @Date: 2018/12/3 11:41
 * @Description:
 */
@Configuration
@Slf4j
public class ServerConfig {

    private ConnectionConfig connectionConfi=  ConnectionConfig.builder().ip("localhost").port(1884).readWriteIdel(60000l).build();

    private final WaterRespository waterRespository;

    public ServerConfig(WaterRespository waterRespository) {
        this.waterRespository = waterRespository;
    }

    @Bean
    public Transport init(){
        ServerTransport serverTransport =new ServerTransport();
        serverTransport .start(()->connectionConfi)
              .subscribe(dulex->{
                  dulex.onReadIdle(() ->()-> {
                      dulex.dispose(); // 关闭连接
                      serverTransport.removeConnection(dulex).block(); // 移除存储connection
                  }).block();
                  dulex.onWriteIdle(() ->()-> {
                      dulex.dispose(); // 关闭连接
                      serverTransport.removeConnection(dulex).block();
                  }).block();
                  dulex.receiveMsg(Content.class)
                          .filter(content -> content.getType().equals(Type.BJ))
                          .subscribe(content -> {
                                log.info("content:",content);
                                // 存储
                          });
              });
         return  serverTransport;
    }





}
