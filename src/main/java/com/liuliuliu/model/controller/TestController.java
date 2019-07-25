package com.liuliuliu.model.controller;

import com.liuliuliu.model.dao.TestDao;
import com.liuliuliu.model.entity.Student;
import com.liuliuliu.model.service.TestService;
import com.liuliuliu.security.userdetails.MyUserDetailsDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对类的描述
 *
 * @author liutianyang
 * @since 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    TestService testService;

    @Autowired
    TestDao testDao;

    @GetMapping
    public String hello(){
        return "hello";
    }

    /**
     * 查询student
     * @param student
     * @return
     */
    @GetMapping("/student")
    public List<Student> queryStudent(Student student){
        return testDao.queryStudent(student);
    }

    @PostMapping("/user")
    public String createUser(@RequestBody MyUserDetailsDao myUserDetailsDao){
        return testService.createUser(myUserDetailsDao);
    }

}
