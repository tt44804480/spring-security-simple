package com.liuliuliu.conf;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * 对类的描述
 *
 * @author liutianyang
 * @since 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test1 {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void test1(){
        boolean matches = passwordEncoder.matches("1234", "$2a$10$X7VfzbHbuSQkEaKWL3Oz0eKx/5w6ft8x5QJCGLUezL8lv6rxjMenS");
        System.out.println("----------------------------------");
        System.out.println(matches);
    }

    @Test
    public void test2(){
        PlaintextPasswordEncoder plaintextPasswordEncoder = new PlaintextPasswordEncoder();
        boolean passwordValid = plaintextPasswordEncoder.isPasswordValid("1234", "1234", null);
        System.out.println(passwordValid);
    }

    @Test
    public void test3() throws InterruptedException {
        List<Thread> list = new ArrayList();

         for(int i = 0;i<20;i++){
             Thread tt = new Thread(new Runnable() {
                 @Override
                 public void run() {
                     System.out.println("hello"+Thread.currentThread().getName());
                 }
             });
             tt.setName("Thread-name-"+i);
             list.add(tt);
         }

         for(int j = 0;j<list.size();j++){
             list.get(j).start();
         }

         Thread.sleep(60000);
    }
}
