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
        // Only disable headless mode when running locally (Render sets RENDER=true)
        if (System.getenv("RENDER") == null) {
            System.setProperty("java.awt.headless", "false");
        }
        SpringApplication.run(HotelApplication.class, args);
    }

    @Bean
    CommandLineRunner openBrowser() {
        return args -> {
            // Skip when deployed on Render or cloud servers
            if (System.getenv("RENDER") != null) {
                return;
            }

            try {
                String url = "http://localhost:8008";
                String os = System.getProperty("os.name").toLowerCase();

                if (os.contains("win")) {
                    Runtime.getRuntime().exec(new String[] {
                        "rundll32", "url.dll,FileProtocolHandler", url
                    });
                } else if (os.contains("nix") || os.contains("nux")) {
                    Runtime.getRuntime().exec(new String[] { "xdg-open", url });
                } else if (os.contains("mac")) {
                    Runtime.getRuntime().exec(new String[] { "open", url });
                } else if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    Desktop.getDesktop().browse(new URI(url));
                }
            } catch (Exception e) {
                System.err.println("Could not open browser automatically: " + e.getMessage());
            }
        };
    }
}