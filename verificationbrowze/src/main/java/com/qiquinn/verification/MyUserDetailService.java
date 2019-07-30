package com.qiquinn.verification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/29
 * @Modified By:
 */
@Component
public class MyUserDetailService implements UserDetailsService
{
    private Logger log = LoggerFactory.getLogger(MyUserDetailService.class);
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("用户名： "+username);
        Collection<GrantedAuthority> authList = new ArrayList<>();
        authList.add(new SimpleGrantedAuthority("user"));
        authList.add(new SimpleGrantedAuthority("admin"));
        return new User(username,passwordEncoder.encode("123456"),authList);
    }
}
