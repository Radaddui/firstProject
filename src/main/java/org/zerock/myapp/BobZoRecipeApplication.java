package org.zerock.myapp;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Arrays;

@Import(WebMvcConfig.class)

@Log4j2

@ServletComponentScan
@SpringBootApplication
public class BobZoRecipeApplication
		extends ServletInitializer
		implements ApplicationListener<ApplicationEvent> {


	public static void main(String[] args) {
		SpringApplication.run(BobZoRecipeApplication.class, args);
		log.trace("main({}) invoked.", Arrays.toString(args));

	} // main

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
//		log.trace("onApplicationEvent({}) invoked.", event);
	} // onApplicationEvent

} // end class
