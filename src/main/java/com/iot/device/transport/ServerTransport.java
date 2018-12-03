package com.iot.device.transport;


import com.iot.device.codec.DeviceDecoder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.ipc.netty.tcp.TcpServer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Supplier;

/**
 * @Auther: lxr
 * @Date: 2018/10/30 11:04
 * @Description:
 */
public class ServerTransport implements   Transport{

    private List<DuplexConnection> connections = new CopyOnWriteArrayList<>();

    public  Flux<DuplexConnection> start(Supplier<ConnectionConfig> configSupplier){
        final ConnectionConfig connectionConfig = configSupplier.get();
        return   Flux.create(fluxSink -> TcpServer.create(ops-> ops.host(connectionConfig.getIp()).port(connectionConfig.getPort()).afterNettyContextInit(afterChannelInit->{
            afterChannelInit
                    .addHandler(new DeviceDecoder());
        })).newHandler(  (in,out)->{
            ReactorDuplexConnection duplexConnection = new ReactorDuplexConnection(in, out, in.context(),this,connectionConfig);
            connections.add(duplexConnection);
            fluxSink.next(duplexConnection);
            return out.context().onClose();
        }).block().onClose());
    }


    @Override
    public  Mono<List<DuplexConnection>> getConnection() {
        return Mono.just(connections);
    }

    @Override
    public Mono<Void> removeConnection(DuplexConnection connection) {
      return   Mono.defer(()->{
            connections.remove(connection);
            return  Mono.empty();
        });
    }


}
