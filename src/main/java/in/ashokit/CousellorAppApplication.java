package in.ashokit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import in.ashokit.entity.Counsellor;
import in.ashokit.service.CounsellorServiceImpl;

@SpringBootApplication
public class CousellorAppApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctxt = SpringApplication.run(CousellorAppApplication.class, args);
		
	}
}
