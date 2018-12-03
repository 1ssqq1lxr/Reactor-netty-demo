package com.iot.device.transport;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.ipc.netty.NettyContext;
import reactor.ipc.netty.NettyInbound;
import reactor.ipc.netty.NettyOutbound;

import java.util.function.Supplier;

/**
 * @Auther: lxr
 * @Date: 2018/10/30 14:46
 * @Description:
 */
public interface DuplexConnection {

    NettyInbound getInbound();

    NettyOutbound getOutbound();

    NettyContext getContext();

    void   dispose();

    Mono<Void>  onReadIdle( Supplier<? extends Runnable> readLe);

    Mono<Void>  onReadIdle(Long l, Supplier<? extends Runnable> readLe);

    Mono<Void>  onWriteIdle( Supplier<? extends Runnable> writeLe);

    Mono<Void>  onWriteIdle(Long l, Supplier<? extends Runnable> writeLe);

     <T> Flux<T> receiveMsg(Class<T> contentClass);
}
