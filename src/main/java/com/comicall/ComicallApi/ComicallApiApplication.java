package com.comicall.ComicallApi;

import com.comicall.ComicallApi.dtos.comics.ComicsCreateRequest;
import com.comicall.ComicallApi.entities.Genre;
import com.comicall.ComicallApi.entities.Role;
import com.comicall.ComicallApi.entities.User;
import com.comicall.ComicallApi.entities.enums.EGenre;
import com.comicall.ComicallApi.services.User.IAuthorService;
import com.comicall.ComicallApi.services.User.IUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ComicallApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComicallApiApplication.class, args);
	}

	@Bean
	CommandLineRunner run(JpaRepository<Genre, Long> genreRepo, IAuthorService authorService, IUserService userService){
		return args -> {
//			genreRepo.save(new Genre(
//					null,
//					EGenre.ACTION,
//					new HashSet<>()
//			));
//
//			genreRepo.save(new Genre(
//					null,
//					EGenre.ADVENTURES,
//					new HashSet<>()
//			));
//
//			userService.saveRole(new Role(
//					null,
//					"READER"
//			));
//
//			userService.saveRole(new Role(
//					null,
//					"ADMIN"
//			));
//
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
//
//			userService.addRoleToUser("test", "ADMIN");
//
//			authorService.createComics(new ComicsCreateRequest(
//					"Кроличья нора",
//					 "Описание",
//					2015,
//					"Путь до постера",
//					"test"
//			));
//			Set<EGenre> genres = new HashSet<>();
//			genres.add(EGenre.ACTION);
//			System.out.println(authorService.addGenresToComics("Кроличья нора", genres));
		};
	}
}
