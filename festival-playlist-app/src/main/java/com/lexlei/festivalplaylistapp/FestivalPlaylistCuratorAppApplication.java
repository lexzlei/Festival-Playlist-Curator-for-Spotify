package com.lexlei.festivalplaylistapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class FestivalPlaylistCuratorAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FestivalPlaylistCuratorAppApplication.class, args);		
	}


}
