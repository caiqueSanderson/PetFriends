package com.infnet.petfriends.petfriends_transporte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
public class PetFriendsTransporteApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetFriendsTransporteApplication.class, args);
	}

}
