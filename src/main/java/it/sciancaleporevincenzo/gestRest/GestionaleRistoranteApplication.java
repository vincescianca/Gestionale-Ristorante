package it.sciancaleporevincenzo.gestRest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@PropertySource(value = "file:${catalina.home}/conf/gestRest.properties", ignoreResourceNotFound = true) //properties di ambiente presenti su tomcat
@EnableScheduling
public class GestionaleRistoranteApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionaleRistoranteApplication.class, args);
	}


}
