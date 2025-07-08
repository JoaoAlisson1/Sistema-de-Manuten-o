package br.ufsm.csi.aerocarespring;

import br.ufsm.csi.aerocarespring.model.Mecanico;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AeroCareSpringApplication  extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Mecanico.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(AeroCareSpringApplication.class, args);
	}

}
