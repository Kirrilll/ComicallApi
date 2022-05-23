package com.comicall.ComicallApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ComicallApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComicallApiApplication.class, args);
	}

//	@Bean
//	CommandLineRunner run(JpaRepository<Genre, Long> genreRepo, IAuthorService authorService, IUserService userService){
//		return args -> {
//			userService.saveUser(
//					new User(
//							null,
//							"test",
//							"test",
//							new HashSet<>(),
//							new HashSet<>(),
//							new HashSet<>()
//					)
//			);
//		};
//	}
}
