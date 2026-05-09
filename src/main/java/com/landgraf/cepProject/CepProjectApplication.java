package com.landgraf.cepProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CepProjectApplication {

	//TODO ADICIONAR SWAGGER NO PROJETO
	//TODO COLOCAR LOMBOK EM TODOS OS LUGARES POSSIVEIS, SET, GET, CONSTRUCTORS, HASHCODE, EQUALS
	
	//TODO Objetos de camadas diferentes não se misturam, se misturam apenas na camada de serviço
	//CONTROLLER <- camada 1 DTO 
	//SERVICE <- camada 2 DTO, ENTITY
	//REPOSITORY <- camada 3 ENTITY
	
	//A
	static void main(String[] args) {
		SpringApplication.run(CepProjectApplication.class, args);
	}

}
