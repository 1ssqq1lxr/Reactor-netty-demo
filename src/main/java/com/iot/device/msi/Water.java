package com.iot.device.msi;

import lombok.Data;

/**
 * @Auther: lxr
 * @Date: 2018/10/30 13:46
 * @Description:
 */
@Data
public class Water {

    private String iccId; // 设备标识

    private String projectId; // 项目标识

    private String heartSeq; // 心跳包序号

    private String pm1; // pm1.0

    private String pm25; // pm2.5

    private String pm10; //pm10

    private String temperature; // 温度

    private String humidity; // 英文

    private String co2;

    private String tvoc;

    private String formaldehyde; // 甲醛

    private String o2;

    private String ion; // 负离子

    private String onOffState; // 开关机

    private String heartTime; // 心跳时间


    public static Water toWater(String[] params){
        Water water =new Water();
        water.setIccId(params[0]);
        water.setProjectId(params[1]);
        water.setHeartSeq(params[2]);
        water.setPm1(params[3]);
        water.setPm25(params[4]);
        water.setPm10(params[5]);
        water.setTemperature(params[6]);
        water.setHumidity(params[7]);
        water.setCo2(params[8]);
        water.setTvoc(params[9]);
        water.setFormaldehyde(params[10]);
        water.setO2(params[11]);
        water.setIon(params[12]);
        water.setOnOffState(params[13]);
        water.setHeartTime(params[14]);
        return water;
    }


}
