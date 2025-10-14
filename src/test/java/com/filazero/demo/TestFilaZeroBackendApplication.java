package com.filazero.demo;

import org.springframework.boot.SpringApplication;

public class TestFilaZeroBackendApplication {

	public static void main(String[] args) {
		SpringApplication.from(FilaZeroBackendApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
