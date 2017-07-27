package core;


import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import annotations.EzEve;

@SpringBootApplication
@RestController
public class TestAnnotationApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestAnnotationApplication.class, args);
	}
	
	
	@RequestMapping("/")
	@EzEve(eventType="test_Run")
	public String gethome() {
		return new String("UP");
	}
	
}



