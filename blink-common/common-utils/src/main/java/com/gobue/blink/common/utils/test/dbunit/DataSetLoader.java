package com.gobue.blink.common.utils.test.dbunit;

import com.github.springtestdbunit.dataset.AbstractDataSetLoader;
import com.google.common.io.Files;
import com.jd.y.ipc.saas.common.exception.AppBusinessException;
import com.jd.y.ipc.saas.common.test.JSONDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.util.fileloader.CsvDataFileLoader;
import org.springframework.core.io.Resource;

public class DataSetLoader extends AbstractDataSetLoader {
    @Override
    protected IDataSet createDataSet(Resource resource) throws Exception {
        if (resource.getFile().isDirectory()) {
            return new CsvDataFileLoader().loadDataSet(resource.getURL());
        }

        String fileNameExtension = Files.getFileExtension(resource.getFile().getName());
        if (fileNameExtension.equalsIgnoreCase("json")) {
            return new JSONDataSet(resource.getFile());
        }

        throw new AppBusinessException("仅支持json和csv数据初始化");
    }
}
