package com.home.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.util.*;

@Controller
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/home2", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        logger.info("Welcome home! The Client locale is {}.", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);

        model.addAttribute("serverTime", formattedDate);

        return "home";
    }

    /**
     * Tiles를 사용하지 않은 일반적인 형태
     */
    @RequestMapping("/test.do")
    public String test() {
        return "test";
    }

    /**
     * Tiles를 사용(header, left, footer 포함)
     */
    @RequestMapping("/testPage.do")
    public String testPage() {
        return "test.page";
    }

    /**
     * Tiles를 사용(header, left, footer 제외)
     */
    @RequestMapping("/testPart.do")
    public String testPart() {
        return "test.part";
    }

    @RequestMapping(value = "/")
    public String index(HttpServletRequest req, HttpServletResponse resp, Model model) throws ServletException, IOException {
        model.addAttribute("Title", "Index Title");
        model.addAttribute("Content", "Index Content");

        List list = new ArrayList<String>();
        list.add("111");
        list.add("222");
        list.add("333");
        model.addAttribute("list", list);
        return "index";
    }

    public void foo() {
        String strings = {"foo", "bar"};

        List<String> l = Arrays.asList();
    }


/*
    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }*/
}
