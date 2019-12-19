package com.gobue.blink.common.utils.test;

import com.jd.y.ipc.saas.common.model.SaaSContext;
import com.jd.y.ipc.saas.common.utils.ThreadLocalUtils;
import org.springframework.data.domain.PageRequest;

import static org.mockito.Mockito.when;

public abstract class BaseTest {
    protected final PageRequest DEFAULT_PAGE = new PageRequest(0, Integer.MAX_VALUE);
    public static final String DEFAULT_TENANT_CODE = "foo";
    public static final long DEFAULT_ACCOUNT_ID = 101L;

    protected void switchTo() {
        stubAccountHolder(TestHelper.anyString(10), TestHelper.anyLong(10000));
    }

    protected void switchToTenant(String tenantCode) {
        when(getSaasContext().getTenantId()).thenReturn(tenantCode);
    }

    protected void switchToAccountId(long id) {
        when(getSaasContext().getUserId()).thenReturn(id + "");
    }


    protected void switchToDefault() {
        stubAccountHolder(DEFAULT_TENANT_CODE, DEFAULT_ACCOUNT_ID);
    }

    protected void switchTo(String tenantCode, Long accountId) {
        SaaSContext saaSContext = getSaasContext();
        when(saaSContext.getTenantId()).thenReturn(tenantCode);
    }

    private void stubAccountHolder(String tenantCode, Long accountId) {
        SaaSContext saaSContext = getSaasContext();
        when(saaSContext.getTenantId()).thenReturn(tenantCode);
        when(saaSContext.getUserId()).thenReturn("" + accountId);
    }

    public SaaSContext getSaasContext() {
        return ThreadLocalUtils.getSaaSContext();
    }
}
