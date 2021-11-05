package com.nosql.laboratorio;

import com.github.javafaker.Faker;
import com.nosql.laboratorio.dao.UserRepository;
import com.nosql.laboratorio.models.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication
public class LaboratorioApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaboratorioApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UserRepository repository) {
		return args -> {
			repository.deleteAll();

			Faker faker = new Faker(new Locale("es"));
			int num = 100;

			List<User> users = generateRandomUsers(faker, num);
			repository.insert(users);
		};
	}

	private List<User> generateRandomUsers(Faker faker, int num) {
		List<User> users = new ArrayList<>();

		for(int i=0; i<num;i++){
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String email = faker.internet().emailAddress(
					firstName.toLowerCase().replace(" ", "")
							.concat(lastName.toLowerCase().replace(" ", ""))
			);
			String password = faker.internet().password();
			List<String> roles = generateRoles();

			User user = new User(
					email,
					password,
					firstName,
					lastName,
					roles
			);

			users.add(user);
		}
		return users;
	}

	private List<String> generateRoles(){
		List<String> roles = Arrays.asList("Admin", "Viewer", "Editor", "Creator");

		int maxRoles = roles.size();
		int minRoles = 0;
		Random rand = new Random();
		int randomNumRoles = rand.nextInt(maxRoles-minRoles) + minRoles;

		return roles.subList(0, randomNumRoles+1);
	}
}
