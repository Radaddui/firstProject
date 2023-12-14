package org.zerock.myapp;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
public class EncodingConfig {

    @Bean
    public FilterRegistrationBean<CharacterEncodingFilter> myCharacterEncodingFilter() {
        FilterRegistrationBean<CharacterEncodingFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new CharacterEncodingFilter());
        filterRegistrationBean.addInitParameter("encoding", "UTF-8");
        filterRegistrationBean.addInitParameter("forceEncoding", "true");
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
} // end class
