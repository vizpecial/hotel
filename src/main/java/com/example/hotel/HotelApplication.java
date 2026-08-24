package com.example.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

import java.awt.Desktop;
import java.net.URI;

@SpringBootApplication
public class HotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelApplication.class, args);
	}
}
