package com.demus.config;

import com.demus.filters.AuthFilter;
import com.demus.repository.SessionRepository;
import com.demus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class FilterConfig {
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    UserRepository userRepository;

    @Bean
    public FilterRegistrationBean<AuthFilter> authenticationFilter(){
        FilterRegistrationBean<AuthFilter> registrationBean
                = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthFilter(this.sessionRepository, this.userRepository));
        registrationBean.addUrlPatterns("/api/*");
        registrationBean.setOrder(1);

        return registrationBean;
    }
}
