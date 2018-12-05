package com.iot.device.service;

import com.iot.device.domian.WaterBean;
import reactor.core.publisher.Mono;

public interface WaterService {

    Mono<Void> save(WaterBean waterBean);
}
