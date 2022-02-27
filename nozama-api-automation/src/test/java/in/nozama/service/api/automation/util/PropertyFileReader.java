package in.nozama.service.api.automation.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {

	private static final String NOZAMA_APIGATEWAY_URL = "http://localhost:8090/";
	private static final String NOZAMA_API_GATEWAY_URL = "nozama.api-gateway.url";
	private static final String NOAMA_API_AUTOMATION_PROPERTIES_FILENAME = "nozama_api_automation.properties";
	public static final String TEST_RESOURCEPATH_PROPERTIES_KEY = "test.resourcepath";

	private Properties properties;

	private final String propertyFilePath = String.format("src//test//resources//%s",
			NOAMA_API_AUTOMATION_PROPERTIES_FILENAME);

	public PropertyFileReader() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(
					String.format("%s not found at %s", NOAMA_API_AUTOMATION_PROPERTIES_FILENAME, propertyFilePath));
		}
	}

	public String getTestResourcePath() {
		return properties.getProperty(TEST_RESOURCEPATH_PROPERTIES_KEY);
	}

	public String getAPIGateWayURL() {
		String apigatewayURL = properties.getProperty(NOZAMA_API_GATEWAY_URL);
		if (apigatewayURL != null)
			return apigatewayURL;

		return NOZAMA_APIGATEWAY_URL;
		/*
		 * throw new RuntimeException( String.format("%s not specified in the %s file.",
		 * NOZAMA_API_GATEWAY_URL, propertyFilePath));
		 */
	}

}
