/**
 * This simplified application made for the purpose of assessment. The task is to implement a REST API that can
 * manage basic cryptocurrency data. The API must support adding, reading, updating and deleting records &
 * the records must be stored in an in memory database. The API must also support paging and sorting for the endpoint
 * /api/currencies.
 *
 * @author Esubalew A Demissie
 *
 */

package com.pharmapartners.opdracht;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OpdrachtApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpdrachtApplication.class, args);
	}

}



