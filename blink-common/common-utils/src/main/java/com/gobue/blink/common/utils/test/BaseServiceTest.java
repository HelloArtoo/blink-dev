package com.gobue.blink.common.utils.test;


import com.jd.y.ipc.saas.common.model.SaaSContext;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public abstract class BaseServiceTest extends BaseTest {
    @Mock
    protected SaaSContext saaSContext;

    @Override
    public SaaSContext getSaasContext() {
        return saaSContext;
    }

    @Before
    public void setupAccountHolder() {
        switchToDefault();
    }
}
