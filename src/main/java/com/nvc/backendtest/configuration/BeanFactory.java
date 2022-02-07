package com.nvc.backendtest.configuration;

import com.nvc.backendtest.service.UserService;
import com.nvc.backendtest.service.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.nvc.backendtest.dataaccess")
public class BeanFactory {

    @Bean
    public UserService getUserService() {
        return new UserServiceImpl();
    }

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
