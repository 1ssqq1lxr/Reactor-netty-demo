package com.iot.device.transport;

import lombok.Builder;
import lombok.Data;

/**
 * @Auther: lxr
 * @Date: 2018/11/13 15:18
 * @Description:
 */
@Data
@Builder
public class ConnectionConfig {

    private String ip;

    private int port;

    private Long  readWriteIdel;

}
