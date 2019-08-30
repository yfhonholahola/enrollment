package com.studentmgr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.studentmgr.dao.impl.CoursesDaoImpl;

@SpringBootApplication
public class App extends SpringBootServletInitializer{
	
	private static final Logger logger = LoggerFactory.getLogger(App.class);
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(App.class);
	}
	
    public static void main( String[] args )
    {
        logger.info("Start Application Now");
    	SpringApplication.run(App.class, args);   
    }
}
