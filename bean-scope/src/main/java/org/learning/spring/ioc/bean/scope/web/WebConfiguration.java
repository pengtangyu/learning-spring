package org.learning.spring.ioc.bean.scope.web;

import org.learning.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class WebConfiguration {

    @Bean
    @RequestScope
    public User user(){
        User user = new User();
        user.setName("pty");
        return user;
    }
}
