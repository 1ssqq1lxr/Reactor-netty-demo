package com.iot.device.msi;

import lombok.Data;

/**
 * @Auther: lxr
 * @Date: 2018/11/5 10:16
 * @Description:
 */
@Data
public class Content {

    private Type type;

    private Water water;

    private Short register;

    private Short value;

    private Short crc;

}
