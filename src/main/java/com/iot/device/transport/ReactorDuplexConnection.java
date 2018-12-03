package com.iot.device.transport;

import lombok.Data;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.ipc.netty.NettyContext;
import reactor.ipc.netty.NettyInbound;
import reactor.ipc.netty.NettyOutbound;

import java.util.function.Supplier;

/**
 * @Auther: lxr
 * @Date: 2018/11/4 17:30
 * @Description:
 */
@Data
public class ReactorDuplexConnection implements  DuplexConnection{

    private NettyInbound inbound;

    private NettyOutbound outbound;

    private NettyContext context;

    private  ConnectionConfig connectionConfig;


    public ReactorDuplexConnection(NettyInbound inbound, NettyOutbound outbound, NettyContext context, Transport transport, ConnectionConfig connectionConfig) {
        this.inbound = inbound;
        this.outbound = outbound;
        this.context = context;
        this.connectionConfig=connectionConfig;
        context.onClose(() -> {
            context.dispose();
            transport.removeConnection(this).block();
        });
    }

    @Override
    public void dispose() {
        context.dispose();
    }

    @Override
    public Mono<Void> onReadIdle(Supplier<? extends Runnable> readLe) {
        return onReadIdle(connectionConfig.getReadWriteIdel(),readLe);
    }

    @Override
    public Mono<Void> onReadIdle(Long l, Supplier<? extends Runnable> readLe) {
        return   Mono.defer(()->{
            inbound.onReadIdle(l,readLe.get());
            return  Mono.empty();
        });
    }

    @Override
    public Mono<Void> onWriteIdle(Supplier< ? extends Runnable> writeLe) {
        return  onWriteIdle(connectionConfig.getReadWriteIdel(),writeLe) ;
    }

    @Override
    public Mono<Void> onWriteIdle(Long l, Supplier<? extends Runnable> write) {
        return   Mono.defer(()->{
            outbound.onWriteIdle(l,write.get());
            return  Mono.empty();
        });
    }

    @Override
    public <T> Flux<T> receiveMsg(Class<T> contentClass) {
        return inbound.receiveObject().cast(contentClass);
    }
}
