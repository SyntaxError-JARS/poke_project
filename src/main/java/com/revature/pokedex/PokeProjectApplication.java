package com.revature.pokedex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // this is @Configuration, @ComponentScan and @EnableAutoConfiguration
public class PokeProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(PokeProjectApplication.class, args);
    }

}