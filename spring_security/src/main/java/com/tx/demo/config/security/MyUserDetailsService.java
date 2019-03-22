package com.tx.demo.config.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author tianxin
 * @Date 2019/1/30
 * @Description:
 */


@Component
public class MyUserDetailsService implements UserDetailsService {
    //@Autowired
    //由于是演示这里就不再创建service层了，直接注入UserRepository。
    //private UserRepository userRepository;


    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //查询账号是否存在，是就返回一个UserDetails的对象，否就抛出异常！
        com.tx.demo.entity.User user = new com.tx.demo.entity.User("tianxin", "1234");
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        for (Role role : user.getRoles()) {
////            authorities.add(new SimpleGrantedAuthority(role.getName()));
////        }
////
        authorities.add(new SimpleGrantedAuthority("admin"));
        if (user == null) {
            throw new UsernameNotFoundException("UserName " + userName + " not found");
        }

        // 返回一个对象给前台
        return new User(user.getUserName(), user.getPassWord(), authorities);
    }


}
