package com.gobue.blink.common.utils.model;

import lombok.Data;

@Data
public class RedisInfo {
    //主键
    private Integer id;
    //住户编号
    private String tenantId;
    //存储使用类型（1：单个；2：混用）
    private Integer useType;
    //redis host
    private String host;
    //redis 密码
    private String password;
    //redis端口号
    private Integer port;
    //超时时间
    private Integer timeout;
}
