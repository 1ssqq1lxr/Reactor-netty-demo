package com.iot.device.domian;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

/**
 * @Auther: lxr
 * @Date: 2018/10/30 13:46
 * @Description:
 */
@Data
@Entity
@Table(name = "WaterBean")
public class WaterBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "icc_id")
    private String iccId; // 设备标识

    @Column(name = "project_id")
    private String projectId; // 项目标识

    @Column(name = "heart_seq")
    private String heartSeq; // 心跳包序号

    @Column(name = "pm1")
    private String pm1; // pm1.0

    @Column(name = "pm25")
    private String pm25; // pm2.5

    @Column(name = "pm10")
    private String pm10; //pm10

    @Column(name = "temperature")
    private String temperature; // 温度

    @Column(name = "humidity")
    private String humidity; // 英文

    @Column(name = "co2")
    private String co2;

    @Column(name = "tvoc")
    private String tvoc;

    @Column(name = "formaldehyde")
    private String formaldehyde; // 甲醛

    @Column(name = "o2")
    private String o2;

    @Column(name = "ion")
    private String ion; // 负离子

    @Column(name = "onOffState")
    private String onOffState; // 开关机

    @Column(name = "heartTime")
    private String heartTime; // 心跳时间




}
