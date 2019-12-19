package com.gobue.blink.common.utils.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaaSContext {

    private String tenantId;       //租户ID
    private String appId;          // 应用ID
    private String userId;         // 用户ID
    private int userType;          //LOGIN_USER_JDPASSPORT=1, LOGIN_USER_JDERP=2 为系统支持类型，其他用户类型应用自行处理
    private String locale;         //国际化标识zh_CN
    private String requestNo;     //请求流水号，全链路唯一标识
    private String signature;     //签名
    private String dataSourceId;  //数据库ID
    private String timeZone; // 时区 Asia/Shanghai

    public SaaSContext(String tenantId, String userId) {
        this.tenantId = tenantId;
        this.userId = userId;
    }

    public static SaaSContext of(SaaSContext saaSContext) {
        try {
            SaaSContext holder = new SaaSContext();
            holder.tenantId = saaSContext.getTenantId();
            holder.locale = saaSContext.getLocale();
            holder.userId = saaSContext.getUserId();
            return holder;
        } catch (Exception var2) {
            return new SaaSContext();
        }
    }
}
