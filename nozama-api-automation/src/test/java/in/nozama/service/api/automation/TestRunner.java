package in.nozama.service.api.automation;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
//Specify the Feature file location
@CucumberOptions(
		dryRun = false,
		plugin = {"pretty"},
		features = {"src/test/features"},
		publish = true
		)
public class TestRunner {
	
	public TestRunner() {
		
	}
	
	//Our Test Runner will search the "sr/test/feature" for the feature files.

}
