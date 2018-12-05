package com.iot.device.connfig;

import com.iot.device.domian.WaterBean;
import com.iot.device.msi.Content;
import com.iot.device.msi.Water;
import com.iot.device.service.WaterService;
import com.iot.device.transport.ConnectionConfig;
import com.iot.device.transport.ServerTransport;
import com.iot.device.transport.Transport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: lxr
 * @Date: 2018/12/3 11:41
 * @Description:
 */
@Configuration
@Slf4j
public class ServerConfig {

    private ConnectionConfig connectionConfi=  ConnectionConfig.builder().ip("localhost").port(1884).readWriteIdel(60000l).build();

    private final WaterService waterService;

    public ServerConfig(WaterService waterService) {
        this.waterService = waterService;
    }

    @Bean
    public Transport init(){
        ServerTransport serverTransport =new ServerTransport();
        serverTransport .start(()->connectionConfi)
              .subscribe(dulex->{
                  dulex.onReadIdle(() ->()-> {
                      dulex.dispose(); // 关闭连接
                      serverTransport.removeConnection(dulex).subscribe(); // 移除存储connection
                  }).block();
                  dulex.onWriteIdle(() ->()-> {
                      dulex.dispose(); // 关闭连接
                      serverTransport.removeConnection(dulex).subscribe();
                  }).block();
                  dulex.receiveMsg(Content.class)
//                          .filter(content -> content.getType().equals(Type.BJ))
                          .subscribe(content -> {
                              log.info("content:",content);
                                switch (content.getType()){
                                    case BJ:
                                        // 存储
                                        Water water=content.getWater();
                                        WaterBean bean = new WaterBean();
                                        BeanUtils.copyProperties(water,bean);
                                        waterService.save(bean).subscribe();
                                        break;
                                    case _0103:
                                        break;
                                    case _0106:
                                }
                          });
              });
         return  serverTransport;
    }





}
