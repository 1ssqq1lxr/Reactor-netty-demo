package com.iot.device.respository;

import com.iot.device.domian.WaterBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Auther: lxr
 * @Date: 2018/12/3 16:50
 * @Description:
 */
@Repository
public interface WaterRespository  extends JpaRepository<WaterBean, Long> {
}
