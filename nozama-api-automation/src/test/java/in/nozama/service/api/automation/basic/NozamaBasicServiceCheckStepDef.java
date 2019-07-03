package in.nozama.service.api.automation.basic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.net.ConnectException;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import in.nozama.service.api.automation.NozamaAutomationProperties;
import io.cucumber.core.logging.Logger;
import io.cucumber.core.logging.LoggerFactory;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class NozamaBasicServiceCheckStepDef {

	private static final Logger LOG = LoggerFactory.getLogger(NozamaBasicServiceCheckStepDef.class);
	private static NozamaAutomationProperties properties;

	private String APIUri;
	private Response response;

	@Given("Nozama API Gateway URL \"([^\"]*)\"$")
	public void nozamaApiGatewayURL(String APIUri) {
		this.APIUri = APIUri;
	}

	@When("^Calling the Nozama API Gateway$")
	public void callingTheNozamaAPIGateway() throws Throwable {
		try {
			response = RestAssured.get(APIUri);
			LOG.info(String.format("API Gate way is Called and Status Code Returned is %d", response.getStatusCode()));
		} catch (Exception e) {
			if (e instanceof ConnectException) {
				fail(String.format("Unable to Reach the Server : %s", APIUri));
			}
		}

	}

	@Then("^I verify the Nozama API Gateway is up$")
	public void iVerifyTheNozamaAPIGatewayIsUp() throws Throwable {
		if (response != null) {
			assertEquals(200, response.getStatusCode());
		}
	}

}
