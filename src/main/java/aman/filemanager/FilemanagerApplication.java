package aman.filemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class FilemanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilemanagerApplication.class, args);
	}
}
