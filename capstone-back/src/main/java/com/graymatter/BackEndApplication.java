package com.graymatter;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackEndApplication {
	 
	public static void main(String[] args) {
		
				//logger.trace("FATAL ERROR");
	
		SpringApplication.run(BackEndApplication.class, args);
		System.out.println("Hello World");
	}

}
