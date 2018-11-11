package com.elliot.fastdfssample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@SpringBootApplication
//@Controller
public class FastdfsServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastdfsServerApplication.class, args);
	}

//	@RequestMapping("/test")
//	@ResponseBody
//	public String test() {
//		return "test";
//	}
}
