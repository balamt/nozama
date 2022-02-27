package in.nozama.service.api.automation.basic;

import java.net.ConnectException;

import in.nozama.service.api.automation.NozamaAutomationProperties;
import in.nozama.service.api.automation.model.UserRequest;
import in.nozama.service.api.automation.util.LoadJsonToObject;
import in.nozama.service.api.automation.util.NozamaApiAutomationUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class NozamaBasicServiceCheckStepDef {

	private static final Logger LOG = LoggerFactory.getLogger(NozamaBasicServiceCheckStepDef.class);
	NozamaAutomationProperties properties = NozamaAutomationProperties.getInstance();

	private String APIUri;
	private Response response;
	private UserRequest userRequest;

	@Given("Nozama API Gateway URL {string}")
	public void nozamaApiGatewayURL(String contextPath) {
		constructEndpointUrl(contextPath);
	}

	@When("^Calling the Nozama API Gateway$")
	public void callingTheNozamaAPIGateway() throws Throwable {
		try {
			response = RestAssured.get(APIUri);
			//LOG.info(String.format("API Gate way is Called and Status Code Returned is %d",	response.getStatusCode()));
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

	@Given("Nozama User Service with API URL {string}")
	public void nozamaUserServiceWithAPIURL(String contextPath) {
		constructEndpointUrl(contextPath);
	}

	private void constructEndpointUrl(String contextPath) {
		this.APIUri = String.format("%s%s",properties.getProperties().getAPIGateWayURL(), contextPath);
	}

	@When("Calling the Nozama User Service Via API Gateway")
	public void callingTheNozamaUserServiceViaAPIGateway() {
		try {
			response = RestAssured.get(APIUri);
			//LOG.info(String.format("API Gate way is Called and Status Code Returned is %d",	response.getStatusCode()));
		} catch (Exception e) {
			if (e instanceof ConnectException) {
				fail(String.format("Unable to Reach the Server : %s", APIUri));
			}
		}
	}

	@Then("I verify the Nozama User Service Status is up")
	public void iVerifyTheNozamaUserServiceStatusIsUp() {
		if (response != null) {
			assertEquals(200, response.getStatusCode());
		}
	}

	@Given("Nozama User Signup Service with API URL {string}")
	public void nozamaUserSignupServiceWithAPIURL(String contextPath) {
		constructEndpointUrl(contextPath);
	}

	@When("Calling the Nozama User Signup Service Via API Gateway Using {string}")
	public void callingTheNozamaUserSignupServiceViaAPIGatewayUsing(String jsonFile) {
		try {
			userRequest = LoadJsonToObject.getUserRequestFrom(jsonFile);
			userRequest = NozamaApiAutomationUtil.randomizeUserRequest(userRequest);
			LOG.error(userRequest.getEmail());
			RestAssured.baseURI = APIUri;
			RequestSpecification request = RestAssured.given();
			request.contentType(ContentType.JSON);
			request.body(userRequest);
			response = request.post(APIUri);
			LOG.error(response.statusLine());
		} catch (Exception e) {
			if (e instanceof ConnectException) {
				fail(String.format("Unable to Reach the Server : %s", APIUri));
			}else{
				fail(String.format("Execution failed : %s", e.getMessage()));
			}
		}
	}

	@Then("I verify the Signup Nozama User Service is created")
	public void iVerifyTheSignupNozamaUserServiceIsCreated() {
		assertNotNull(userRequest);
		assertEquals(userRequest.getUsertype(), "BASIC");
		assertEquals(201, response.getStatusCode());
	}
}
