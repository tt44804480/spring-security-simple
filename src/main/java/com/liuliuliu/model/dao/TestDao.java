package com.liuliuliu.model.dao;

import com.liuliuliu.model.entity.Student;
import com.liuliuliu.security.userdetails.MyUserDetailsDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestDao {

    List<Student> queryStudent(Student student);

    MyUserDetailsDao queryUserDetailsByUsername(String username);

    MyUserDetailsDao  queryUserDetailsByMobile(String mobile);

    void insertUser(MyUserDetailsDao  myUserDetailsDao);
}
