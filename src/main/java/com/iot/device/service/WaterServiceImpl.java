package com.iot.device.service;

import com.iot.device.domian.WaterBean;
import com.iot.device.respository.WaterRespository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

/**
 * @Auther: lxr
 * @Date: 2018/12/3 17:47
 * @Description:
 */
@Service
@Transactional
public class WaterServiceImpl implements WaterService {

    private final WaterRespository waterRespository;

    public WaterServiceImpl(WaterRespository waterRespository) {
        this.waterRespository = waterRespository;
    }

    @Override
    public Mono<Void> save(WaterBean waterBean) {
        return Mono.defer(()->{
            waterRespository.save(waterBean);
            return Mono.empty();
        });
    }
}
