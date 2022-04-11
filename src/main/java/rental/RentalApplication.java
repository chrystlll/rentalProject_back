package rental;


import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import rental.logger.LOGG;


@SpringBootApplication
public class RentalApplication {

	
	
	public static void main(String[] args) {
		
		Logger LOGGER = LOGG.getLogger(RentalApplication.class);
		LOGGER.info("Rental application started.");		
		SpringApplication.run(RentalApplication.class, args);
	}
}
