package com.tx.demo.config.security;

import com.tx.demo.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

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

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //查询账号是否存在，是就返回一个UserDetails的对象，否就抛出异常！
        User user = new User("tianxin","1234");
        if (user == null) {
            throw new UsernameNotFoundException("UserName " + userName + " not found");
        }

        // 返回一个对象给前台
        return null;
    }


}
