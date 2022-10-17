package com.mcb;

import com.mcb.repository.UserDao;
import com.mcb.service.JwtUserDetailsService;
import com.mcb.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class JwtUserDetailsServiceUT {

    @Autowired
    private JwtUserDetailsService service;

    @MockBean
    private UserDao userDao;


    //register

    @Test
    public void userJwtRegisterTest(){
        User user = new User(1,"Mcbnayam","password");
        when(userDao.save(user)).thenReturn(user);
        assertEquals(user,userDao.save(user));
    }


}
