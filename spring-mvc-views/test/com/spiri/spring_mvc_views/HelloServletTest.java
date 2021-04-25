package com.spiri.spring_mvc_views;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

public class HelloServletTest {
    private String message;

    @Test
    public void init() {
        message = "Hello World!";
        Assert.assertEquals(message, "Hello World!");

    }

    @RunWith(MockitoJUnitRunner.class)
    public void doGet() {
        MyServlet servlet = new MyServlet();
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        req.addParameter("id", "1234");
        req.addParameter("pwd", "1234");
        servlet.doPost(req, res);
        assertEquals(res.getContents(), "OK");
    }
}