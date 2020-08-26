package io.jzheaux.springsecurity.patterns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class PatternsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatternsApplication.class, args);
	}

	@RestController
	static class PatternsController {
		private final LocationService locationService;

		public PatternsController(LocationService locationService) {
			this.locationService = locationService;
		}

		@GetMapping("/")
		public String hello(@CurrentSecurityContext(expression="authentication.name") String name) {
			return "Hello, " + name + "!";
		}

		@PostMapping("/location/{where}")
		public String move(@PathVariable("where") String where) {
			return this.locationService.moveTo(where);
		}
	}

}
