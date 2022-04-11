package rental.logger;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

public class LOGG {

	static Logger logger;
	static LoggerContext context = (LoggerContext) LogManager.getContext(false);
	static File file = new File("src/log4j2.xml");
	

	/**
	 * @return the logger
	 */
	public static Logger getLogger(Class currentClass) {
		context.setConfigLocation(file.toURI());
		logger = LogManager.getLogger(currentClass);
		return logger;
	}

}
