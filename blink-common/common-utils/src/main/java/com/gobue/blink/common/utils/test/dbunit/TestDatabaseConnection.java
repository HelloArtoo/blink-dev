package com.gobue.blink.common.utils.test.dbunit;

import com.jd.y.ipc.saas.common.database.MultiTenantDataSource;
import com.jd.y.ipc.saas.common.model.SaaSContext;
import com.jd.y.ipc.saas.common.test.BaseTest;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

import static org.mockito.Mockito.when;

public class TestDatabaseConnection extends DatabaseDataSourceConnection {

    @Autowired
    @Resource
    SaaSContext saaSContext;

    public TestDatabaseConnection(DataSource dataSource) throws SQLException {
        super(dataSource);
        super.getConfig().setProperty(DatabaseConfig.FEATURE_ALLOW_EMPTY_FIELDS, true);
        super.getConfig().setProperty(DatabaseConfig.FEATURE_QUALIFIED_TABLE_NAMES, true);
        super.getConfig().setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER, new FixedMySqlMetadataHandler());
//        super.getConfig().setProperty(DatabaseConfig.FEATURE_CASE_SENSITIVE_TABLE_NAMES, true);
    }

    @Override
    public Connection getConnection() throws SQLException {

        if (Objects.isNull(saaSContext)) {
            throw new RuntimeException("internal error");
        }

        if (Objects.isNull(saaSContext.getTenantId())) {
            when(saaSContext.getTenantId()).thenReturn(BaseTest.DEFAULT_TENANT_CODE);
        }
        Connection connection = super.getConnection();
        return connection;
    }
}
