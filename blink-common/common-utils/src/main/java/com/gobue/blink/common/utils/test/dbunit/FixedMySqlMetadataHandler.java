package com.gobue.blink.common.utils.test.dbunit;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dbunit.ext.mysql.MySqlMetadataHandler;

public class FixedMySqlMetadataHandler extends MySqlMetadataHandler {

    public ResultSet getTables(DatabaseMetaData metaData, String schemaName, String[] tableType) throws SQLException {

        List<ResultSet> rsList = new ArrayList<ResultSet>();
        ResultSet catRs = metaData.getCatalogs();
        while (catRs.next()) {
            rsList.add(metaData.getTables(catRs.getString(1), null, "%", tableType));
        }
        catRs.close();

        return CombinedResultSet.newInstance(rsList);
    }

    private static class CombinedResultSet implements InvocationHandler {

        private int currentIndex = 0;
        private ResultSet current;
        private List<ResultSet> rsList;

        public CombinedResultSet(List<ResultSet> rsList) {
            this.rsList = rsList;
            current = rsList.get(0);
        }

        public static ResultSet newInstance(List<ResultSet> rsList) {
            return (ResultSet) Proxy.newProxyInstance(CombinedResultSet.class.getClassLoader(), new Class[]{ResultSet.class}, new CombinedResultSet(rsList));
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if ("next".equals(method.getName())) {
                return next();
            }
            if ("close".equals(method.getName())) {
                close();
                return null;
            }

            return method.invoke(current, args);
        }

        private boolean next() throws SQLException {
            if (current.next()) {
                return true;
            } else {

                if (currentIndex >= rsList.size() - 1) {
                    return false;
                }
                while (++currentIndex <= rsList.size() - 1) {
                    current = rsList.get(currentIndex);
                    if (current.next()) {
                        return true;
                    }
                }
                return false;
            }
        }

        private void close() throws SQLException {
            for (ResultSet rs : rsList) {
                rs.close();
            }
        }
    }
}