package com.sweblim.controller;

import com.sweblim.entity.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EndPoint {
    @Autowired
    Agent agent;
    @RequestMapping("/testselenium")
    public String run1(){
        agent.run1();
        return "导出csv文件成功";
    }
    @RequestMapping("/testsikuli")
    public String run2(){
        agent.run2();
        return "";
    }
}
