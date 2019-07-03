package in.nozama.service.api.automation;

import in.nozama.service.api.automation.util.PropertyFileReader;

public class NozamaAutomationProperties {

	private static NozamaAutomationProperties properties;
	private static PropertyFileReader propertyFileReader;

	public static NozamaAutomationProperties getInstance() {
		if (properties == null) {
			properties = new NozamaAutomationProperties();
		}
		return properties;
	}

	private NozamaAutomationProperties() {
		if (propertyFileReader == null) {
			propertyFileReader = new PropertyFileReader();
		}
	}
	
	public PropertyFileReader getProperties() {
		return propertyFileReader;
	}

}
