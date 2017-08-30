package com.kangda.base.security;

import com.kangda.api.IUserService;
import com.kangda.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by shouhan on 2017/8/23.
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private IUserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("")) {
            throw new CaptchaException("用户名为空");
        }
        User user = userService.findByName(username);
        if (user == null) {
            throw new CaptchaException("用户不存在");
        }
        Boolean flag = passwordEncoder.matches("123456", user.getPassWord());
        System.out.println("用户名和密码：" + user.getUsername() + "#" + user.getPassword());
        System.out.println("密码是否匹配：" + flag);
        return user;
    }
}
