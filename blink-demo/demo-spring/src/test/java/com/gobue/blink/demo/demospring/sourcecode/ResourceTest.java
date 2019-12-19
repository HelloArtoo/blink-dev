package com.gobue.blink.demo.demospring.sourcecode;

import org.junit.Test;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

public class ResourceTest {

    @Test
    public void test() throws Exception {
        Enumeration<URL> resources = getClassLoader().getResources("app.properties");
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            System.out.println("###:" + url.getFile());

            Properties properties = PropertiesLoaderUtils.loadProperties(new UrlResource(url));
            properties.entrySet().forEach(entry -> {
                System.out.println("## key:" + entry.getKey() + ", value:" + entry.getValue());
            });
        }
        System.out.println("done....");
    }

    @Test
    public void testSystemReource() throws Exception {
        Enumeration<URL> resources = ClassLoader.getSystemResources("app.properties");
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            System.out.println("###:" + url.getFile());

            Properties properties = PropertiesLoaderUtils.loadProperties(new UrlResource(url));
            properties.entrySet().forEach(entry -> {
                System.out.println("## key:" + entry.getKey() + ", value:" + entry.getValue());
            });
        }
        System.out.println("done....");
    }


    private ClassLoader getClassLoader() throws Exception {
        return Thread.currentThread().getContextClassLoader();
    }
}
