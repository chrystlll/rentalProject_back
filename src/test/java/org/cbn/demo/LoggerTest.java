package org.cbn.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.junit.jupiter.api.Test;


import rental.RentalApplication;

class LoggerTest {

	static Logger logger = LogManager.getLogger(RentalApplication.class);
	static String url = "src/main/resources/logs/logs.txt";

	/** Test if the logger Error and Info works (not Debug) */
	@Test
	void testInsertLog() throws IOException {

		String seqDebug = "Test: Debug Message Logged !!!";
		String seqInfo = "Test: Info Message Logged !!!";
		String seqError = "Test: Error Message Logged !!!";

		LoggerContext context = (LoggerContext) LogManager.getContext(false);
		File file = new File("src/log4j2.xml");
		context.setConfigLocation(file.toURI());

		logger.debug(seqDebug);
		logger.info(seqInfo);
		logger.error(seqError);

		// Read the file
		File initialFile = new File(url);
	    InputStream targetStream = new FileInputStream(initialFile);
		String data = readFromInputStream(targetStream);
		
		assertTrue(data.contains(seqInfo));
		assertTrue(data.contains(seqError));
		assertFalse(data.contains(seqDebug));
	}

	private String readFromInputStream(InputStream inputStream) throws IOException {
		StringBuilder resultStringBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = br.readLine()) != null) {
				resultStringBuilder.append(line).append("\n");
			}
		}
		return resultStringBuilder.toString();
	}

}
