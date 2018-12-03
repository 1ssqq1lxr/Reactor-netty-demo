package com.iot.device.transport;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public interface Transport {

    Flux<DuplexConnection> start(Supplier<ConnectionConfig> configSupplier);

    Mono<List<DuplexConnection>> getConnection();

    Mono<Void> removeConnection(DuplexConnection connection);

}
