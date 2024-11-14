package com.naveen.pauypwgback;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "PayU PWG project",
        description = "A demo project to demonstrate the integration of PayU payment gateway with Spring Boot",
        version = "v1",
        contact = @Contact(
                email = "naveenalla3000@gmail.com",
                name = "Naveen Alla"
        )
))
public class PayUPwgBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayUPwgBackApplication.class, args);
    }

}
