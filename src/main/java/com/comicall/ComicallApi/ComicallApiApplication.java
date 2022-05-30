package com.comicall.ComicallApi;

import com.comicall.ComicallApi.dtos.genres.GenreDTO;
import com.comicall.ComicallApi.entities.Genre;
import com.comicall.ComicallApi.entities.Role;
import com.comicall.ComicallApi.entities.User;
import com.comicall.ComicallApi.repositories.UserRepository;
import com.comicall.ComicallApi.services.admin.AdminService;
import com.comicall.ComicallApi.services.admin.IAdminService;
import com.comicall.ComicallApi.services.author.IAuthorService;
import com.comicall.ComicallApi.services.user.IUserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;

@SpringBootApplication
@SecurityScheme(name = "Comicall-api", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
@OpenAPIDefinition(info = @Info(title = "User API", version = "3.0", description = "User Details"))
public class ComicallApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ComicallApiApplication.class, args);
	}
}
