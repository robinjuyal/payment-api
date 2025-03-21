package com.paymentApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(scanBasePackages = {"com.paymentApi"})
@SpringBootApplication(scanBasePackages = {"com.paymentApi"})

public class PaymentApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentApiApplication.class, args);
	}

}
