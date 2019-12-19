package com.gobue.blink.common.utils.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TenantInfo {
    String tenantId;
    String user;
    String url;
    String password;

    String tenantName;
    LocalDateTime createTime;
    LocalDateTime updateTime;
}
