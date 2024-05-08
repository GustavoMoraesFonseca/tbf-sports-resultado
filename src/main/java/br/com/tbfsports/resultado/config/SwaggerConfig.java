package br.com.tbfsports.resultado.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(title = "Resultados das Corridas", 
	description = "",
	contact = @Contact(name = "Gustavo de Moraes",
	url = "http://quotenow.com.br/", email = "gu.moraes.fonseca@gmail.com"),
	license = @License(name = "MIT Licence")),
	servers = @Server(url = "http://localhost:8080"))

@SecurityScheme(name = "Token",
	scheme = "Bearer", type = SecuritySchemeType.HTTP,
	in = SecuritySchemeIn.HEADER, bearerFormat = "JWT")
public class SwaggerConfig {}