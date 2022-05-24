package com.comicall.ComicallApi;

import com.comicall.ComicallApi.entities.Genre;
import com.comicall.ComicallApi.entities.User;
import com.comicall.ComicallApi.repositories.UserRepository;
import com.comicall.ComicallApi.services.admin.IAdminService;
import com.comicall.ComicallApi.services.author.IAuthorService;
import com.comicall.ComicallApi.services.user.IUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;

@SpringBootApplication
public class ComicallApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComicallApiApplication.class, args);
	}

//	@Bean
//	CommandLineRunner run(IAdminService adminService, UserRepository userRepository){
//		return args -> {
//			adminService.addRoleToUser("mod", "ADMIN");
//		};
//	}
}
