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
// This automatically opens the browser on startup
	@Bean
	CommandLineRunner openBrowser() {
		return args -> {
			try {
				String url = "http://localhost:8008";
                String os = System.getProperty("os.name").toLowerCase();
                
                if (os.contains("win")) {
                    // Windows-specific command to force-open default browser
					Runtime.getRuntime().exec(new String[] {
						"rundll32", "url.dll,FileProtocolHandler", url
					});
                } else if (Desktop.isDesktopSupported()) {
                    // Mac / Linux
                    Desktop.getDesktop().browse(new URI(url));
                }
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	}
}
