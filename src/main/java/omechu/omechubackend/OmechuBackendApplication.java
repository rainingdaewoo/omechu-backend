package omechu.omechubackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OmechuBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(OmechuBackendApplication.class, args);
	}

}
