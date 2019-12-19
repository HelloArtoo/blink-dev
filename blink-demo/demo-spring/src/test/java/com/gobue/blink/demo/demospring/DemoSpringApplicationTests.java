package com.gobue.blink.demo.demospring;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoSpringApplicationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void contextLoads() {
    }


    @Test
    public void testHello() {
    //MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    //params.add("userName", "jack");
        // String value = testRestTemplate.getForObject("http://127.0.0.1:8080/bye/jack", String.class);
        // Assert.assertEquals("bye jack", value);

    }
}
