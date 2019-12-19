package com.gobue.blink.common.utils.test;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.jd.y.ipc.saas.common.model.SaaSContext;
import com.jd.y.ipc.saas.common.test.dbunit.DataSetLoader;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class})
@DbUnitConfiguration(dataSetLoader = DataSetLoader.class, databaseConnection = "testDatabaseConnection")
@MockBean(classes = {SaaSContext.class})
@Slf4j
public abstract class BaseSpringContextTest extends BaseTest {

    @MockBean
    @Resource
    SaaSContext saaSContext;

    @Before
    public void init() {
        switchToDefault();
    }

    @Override
    public SaaSContext getSaasContext() {
        return this.saaSContext;
    }
}
