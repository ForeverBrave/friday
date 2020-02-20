package com.it.friday;

import com.it.friday.domain.SysUser;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class FridayApplicationTests {

    @Test
    public void contextLoads() {
        SysUser sysUser = new SysUser();
        sysUser.setPassword("123456");
        String encode = new BCryptPasswordEncoder().encode(sysUser.getPassword());
        System.out.println("pwd="+encode);
    }

}
