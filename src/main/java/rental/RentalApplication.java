package rental;


import java.io.File;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RentalApplication {

	static Logger logger = LogManager.getLogger(RentalApplication.class);
	
	public static void main(String[] args) {
		
		LoggerContext context = (LoggerContext) LogManager.getContext(false);
		File file = new File("src/log4j2.xml");

		// this will force a reconfiguration
		context.setConfigLocation(file.toURI());
		
		logger.info("Rental application started.");
		
		SpringApplication.run(RentalApplication.class, args);
	}
}
