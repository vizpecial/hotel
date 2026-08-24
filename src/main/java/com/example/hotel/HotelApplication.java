package com.example.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import java.awt.GraphicsEnvironment;

import java.awt.Desktop;
import java.net.URI;

@SpringBootApplication
public class HotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelApplication.class, args);
	}
	// Automatically opens the browser locally, but stays quiet on Render
	@Bean
	CommandLineRunner openBrowser() {
		return args -> {
			// If running on Render or any cloud server, skip opening the browser
			if (System.getenv("RENDER") != null || GraphicsEnvironment.isHeadless()) {
				return;
			}

			try {
				String url = "http://localhost:8008";
				String os = System.getProperty("os.name").toLowerCase();
				
				if (os.contains("win")) {
					Runtime.getRuntime().exec(new String[] {
						"rundll32", "url.dll,FileProtocolHandler", url
					});
				} else if (Desktop.isDesktopSupported()) {
					Desktop.getDesktop().browse(new URI(url));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	}
}
