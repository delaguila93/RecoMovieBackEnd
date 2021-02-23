/**
 *
 * @author Jose Maria del Aguila Lopez
 *
 */

package com.recomovie.app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication(scanBasePackages = "com.recomovie")
@EntityScan(basePackages = "com.recomovie")
@EnableJpaRepositories(basePackages = {"com.recomovie"})
@RestController
public class RecoMovieApplication {

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {

		SpringApplication server = new SpringApplication(RecoMovieApplication.class);
		ApplicationContext context = server.run(args);
		System.out.println("------------------ App Started -------------------");
	}

}
