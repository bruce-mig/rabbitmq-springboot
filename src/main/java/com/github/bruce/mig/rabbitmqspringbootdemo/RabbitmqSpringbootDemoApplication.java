package com.github.bruce.mig.rabbitmqspringbootdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Bruce Migeri
 */

@SpringBootApplication
@EnableScheduling
public class RabbitmqSpringbootDemoApplication {

	@Profile("usage_message")
	@Bean
	public CommandLineRunner usage() {
		return args -> {
			System.out.println("This app uses Spring Profiles to control its behaviour.\n");
			System.out.println("Sample usage: java -jar target/rabbitmq-springboot-demo-0.0.1-SNAPSHOT.jar --spring.profiles.active=hello-world,sender");
		};
	}

	@Profile("!usage_message")
	@Bean
	public CommandLineRunner tutorial() {
		return new RabbitmqSpringbootDemoRunner();
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(RabbitmqSpringbootDemoApplication.class, args);
	}

}
