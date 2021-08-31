package com.sgs.student;

import java.security.KeyStore;
import java.security.KeyStoreException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.dentrassi.crypto.pem.PemKeyStoreProvider;

@SpringBootApplication
public class StudentMonitoringSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentMonitoringSystemApplication.class, args);
	}

}
