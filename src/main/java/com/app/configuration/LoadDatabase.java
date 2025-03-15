package com.app.configuration;

import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.app.entity.Role;
import com.app.entity.UserEntity;
import com.app.repository.UserRepository;

//@Configuration
public class LoadDatabase {

	@Bean
	CommandLineRunner initDatabase(UserRepository userRepository) {
		return arg -> {
			/** Roles **/
			Role roleAdmin = Role.builder()
								 .name("ADMIN")
								 .build();
			
			Role roleUser = Role.builder()
					 			.name("USER")
					 			.build();
			
			Role roleInvited = Role.builder()
		 						   .name("INVITED")
		 						   .build();
			
			Role roleDevelop= Role.builder()
					   			  .name("DEVELOPER")
					   			  .build();
			
			UserEntity userAlex = UserEntity.builder()
											.username("Alexis")
											.password("$2a$10$3S84.aE5GAxLMeXyDUFkruNnoQVE/UOM6iY35vtwirheoBfl7B9qC")
											.isEnabled(true)
											.accountNoExpired(true)
											.accountNoLocked(true)
											.credentialNoExpired(true)
											.roles(Set.of(roleAdmin))
											.build();
			
			UserEntity userJose = UserEntity.builder()
											.username("Jose")
											.password("$2a$10$3S84.aE5GAxLMeXyDUFkruNnoQVE/UOM6iY35vtwirheoBfl7B9qC")
											.isEnabled(true)
											.accountNoExpired(true)
											.accountNoLocked(true)
											.credentialNoExpired(true)
											.roles(Set.of(roleUser))
											.build();
			
			UserEntity userDaniel = UserEntity.builder()
											  .username("Daniel")
											  .password("$2a$10$3S84.aE5GAxLMeXyDUFkruNnoQVE/UOM6iY35vtwirheoBfl7B9qC")
											  .isEnabled(true)
											  .accountNoExpired(true)
											  .accountNoLocked(true)
											  .credentialNoExpired(true)
											  .roles(Set.of(roleInvited))
											  .build();
			
			UserEntity userAndres = UserEntity.builder()
											  .username("Andres")
											  .password("$2a$10$3S84.aE5GAxLMeXyDUFkruNnoQVE/UOM6iY35vtwirheoBfl7B9qC")
											  .isEnabled(true)
											  .accountNoExpired(true)
											  .accountNoLocked(true)
											  .credentialNoExpired(true)
											  .roles(Set.of(roleDevelop))
											  .build();
			
			userRepository.saveAll(List.of(userAlex, userJose, userDaniel, userAndres));
		};
	}
}
