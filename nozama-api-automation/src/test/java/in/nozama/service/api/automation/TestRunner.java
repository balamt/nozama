package in.nozama.service.api.automation;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
//Specify the Feature file location
@CucumberOptions(
		dryRun = false,
		strict = true,
		plugin = {"pretty"},
		features = {"src/test/features"}
		)
public class TestRunner {
	
	public TestRunner() {
		
	}
	
	//Our Test Runner will search the "sr/test/feature" for the feature files.

}
