package br.com.hadryan.ecommerce.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class MsNotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsNotificationApplication.class, args);
	}

}
