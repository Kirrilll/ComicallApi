package com.comicall.ComicallApi.configs;

import com.comicall.ComicallApi.dtos.genres.GenreDTO;
import com.comicall.ComicallApi.dtos.user.RegisterRequest;
import com.comicall.ComicallApi.entities.Role;
import com.comicall.ComicallApi.services.admin.AdminService;
import com.comicall.ComicallApi.services.auth.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitConfig {
    @Bean
    CommandLineRunner run(AdminService adminService, AuthService authService){
		return args -> {
			adminService.saveGenre(new GenreDTO("ДЕТЕКТИВ"));
			adminService.saveGenre(new GenreDTO("ДРАМА"));
			adminService.saveGenre(new GenreDTO("ФЭНТЕЗИ"));
			adminService.saveGenre(new GenreDTO("УЖАСЫ"));
            adminService.saveGenre(new GenreDTO("СУПЕРГЕРОИКА"));
            adminService.saveGenre(new GenreDTO("ТРИЛЛЕР"));
            adminService.saveGenre(new GenreDTO("БОЕВИК"));
            adminService.saveGenre(new GenreDTO("ПОСТАПОКАЛИПТИКА"));
            adminService.saveGenre(new GenreDTO("ФАНТАСТИКА"));
            adminService.saveGenre(new GenreDTO("ЭРОТИКА"));
            adminService.saveGenre(new GenreDTO("КОМЕДИЯ"));
            adminService.saveGenre(new GenreDTO("МИСТИКА"));
            adminService.saveGenre(new GenreDTO("НУАР"));
            adminService.saveRole(new Role(null, "READER"));
            adminService.saveRole(new Role(null, "ADMIN"));
            adminService.saveRole(new Role(null, "AUTHOR"));
            authService.registerAuthor(new RegisterRequest("admin", "admin"));
            adminService.addRoleToUser("admin", "ADMIN");
		};
	}
}
