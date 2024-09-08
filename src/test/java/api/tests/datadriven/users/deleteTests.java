package api.tests.datadriven.users;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.http.Headers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.github.javafaker.Faker;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import api.endpoints.UserEndPoints2;
import api.payload.User;
import api.utils.DataProviders;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class deleteTests {

	User userpayload;

	/* -------------------- DELETE API - TESTS ----------------------- */
	@Test(description = "Validate that API should return API status code within 2XX series", dataProvider = "alldata", dataProviderClass = DataProviders.class, priority = 1)
	public void DeleteUserTestMethod1(String UserId, String UserName, String FIrstName, String LastName, String Email,
			String Password, String Phone, String UserStatus) throws IOException {

		userpayload = new User();

		userpayload.setId(Integer.valueOf(UserId));
		userpayload.setUsername(UserName);
		userpayload.setFirstName(FIrstName);
		userpayload.setLastName(LastName);
		userpayload.setEmail(Email);
		userpayload.setPassword(Password);
		userpayload.setPhone(Phone);
		userpayload.setUserStatus(Integer.valueOf(UserStatus));

		UserEndPoints2.createUser(userpayload); // Pre-requiste for Delete user is to have one user created.

		Response response = UserEndPoints2.deleteUser(this.userpayload.getUsername());
		Matcher<String> matcher1 = matchesPattern("^20[0-9]$");

		assertThat("Assertion 1", matcher1.matches(String.valueOf(response.getStatusCode())));
	}

	@Test(description = "Validate that API should return API status code as 200", dataProvider = "alldata", dataProviderClass = DataProviders.class, priority = 2)
	public void DeleteUserTestMethod2(String UserId, String UserName, String FIrstName, String LastName, String Email,
			String Password, String Phone, String UserStatus) throws IOException {
		userpayload = new User();

		userpayload.setId(Integer.valueOf(UserId));
		userpayload.setUsername(UserName);
		userpayload.setFirstName(FIrstName);
		userpayload.setLastName(LastName);
		userpayload.setEmail(Email);
		userpayload.setPassword(Password);
		userpayload.setPhone(Phone);
		userpayload.setUserStatus(Integer.valueOf(UserStatus));
		UserEndPoints2.createUser(userpayload); // Pre-requiste for Delete user is to have one user created.

		Response response =  UserEndPoints2.deleteUser(this.userpayload.getUsername());
		assertThat(response.getStatusCode(), is(200));

	}

	@Test(description = "Validate that API should return a response payload ie. Non-empty response body and response header", dataProvider = "alldata", dataProviderClass = DataProviders.class, priority = 2)
	public void DeleteUserTestMethod3(String UserId, String UserName, String FIrstName, String LastName, String Email,
			String Password, String Phone, String UserStatus) throws IOException {

		userpayload = new User();

		userpayload.setId(Integer.valueOf(UserId));
		userpayload.setUsername(UserName);
		userpayload.setFirstName(FIrstName);
		userpayload.setLastName(LastName);
		userpayload.setEmail(Email);
		userpayload.setPassword(Password);
		userpayload.setPhone(Phone);
		userpayload.setUserStatus(Integer.valueOf(UserStatus));
		UserEndPoints2.createUser(userpayload); // Pre-requiste for Delete user is to have one user created.

		Response response =  UserEndPoints2.deleteUser(this.userpayload.getUsername());

		String responseBody = response.getBody().toString();
		assertThat(responseBody, not(isEmptyString()));

		String responseHeaders = response.getHeaders().toString();
		assertThat(responseHeaders, not(isEmptyString()));

	}

	@Test(description = "Validate that API should return response of type = JSON", dataProvider = "alldata", dataProviderClass = DataProviders.class, priority = 2)
	public void DeleteUserTestMethod4(String UserId, String UserName, String FIrstName, String LastName, String Email,
			String Password, String Phone, String UserStatus) throws IOException {

		userpayload = new User();

		userpayload.setId(Integer.valueOf(UserId));
		userpayload.setUsername(UserName);
		userpayload.setFirstName(FIrstName);
		userpayload.setLastName(LastName);
		userpayload.setEmail(Email);
		userpayload.setPassword(Password);
		userpayload.setPhone(Phone);
		userpayload.setUserStatus(Integer.valueOf(UserStatus));

		UserEndPoints2.createUser(userpayload); // Pre-requiste for Delete user is to have one user created.

		Response response =  UserEndPoints2.deleteUser(this.userpayload.getUsername());

		assertThat(response.getContentType(), is("application/json"));

	}

	@Test(description = "Validate that API should should adhere to Response structure as per data model mentioned in API Spec or API Doc.- JSON SCHEMA VALIDATION", dataProvider = "alldata", dataProviderClass = DataProviders.class, priority = 2)
	public void DeleteUserTestMethod5(String UserId, String UserName, String FIrstName, String LastName, String Email,
			String Password, String Phone, String UserStatus) throws IOException {

		userpayload = new User();

		userpayload.setId(Integer.valueOf(UserId));
		userpayload.setUsername(UserName);
		userpayload.setFirstName(FIrstName);
		userpayload.setLastName(LastName);
		userpayload.setEmail(Email);
		userpayload.setPassword(Password);
		userpayload.setPhone(Phone);
		userpayload.setUserStatus(Integer.valueOf(UserStatus));

		UserEndPoints2.createUser(userpayload); // Pre-requiste for Delete user is to have one user created.

		Response response = UserEndPoints2.deleteUser(this.userpayload.getUsername());

		assertThat(response.jsonPath().prettyPrint(),
				JsonSchemaValidator.matchesJsonSchemaInClasspath("User\\Put_jsonschemaval.json"));

	}

	@Test(description = "Valdiate that API should return response body with details of all existing users", dataProvider = "alldata", dataProviderClass = DataProviders.class, priority = 2)
	public void DeleteUserTestMethod6(String UserId, String UserName, String FIrstName, String LastName, String Email,
			String Password, String Phone, String UserStatus) throws IOException {

		userpayload = new User();

		userpayload.setId(Integer.valueOf(UserId));
		userpayload.setUsername(UserName);
		userpayload.setFirstName(FIrstName);
		userpayload.setLastName(LastName);
		userpayload.setEmail(Email);
		userpayload.setPassword(Password);
		userpayload.setPhone(Phone);
		userpayload.setUserStatus(Integer.valueOf(UserStatus));

		UserEndPoints2.createUser(userpayload); // Pre-requiste for Delete user is to have one user created.

		Response response =  UserEndPoints2.deleteUser(this.userpayload.getUsername());

		assertThat(response.getBody().jsonPath().get("code"), is(200));
		assertThat(response.getBody().jsonPath().get("type"), is("unknown"));
		assertThat(response.getBody().jsonPath().get("message").toString().length(), is(greaterThanOrEqualTo(6)));

	}

	@Test(description = "Valdiate that API should return response headers as per the spec", dataProvider = "alldata", dataProviderClass = DataProviders.class, priority = 2)
	public void DeleteUserTestMethod7(String UserId, String UserName, String FIrstName, String LastName, String Email,
			String Password, String Phone, String UserStatus) throws IOException {

		userpayload = new User();

		userpayload.setId(Integer.valueOf(UserId));
		userpayload.setUsername(UserName);
		userpayload.setFirstName(FIrstName);
		userpayload.setLastName(LastName);
		userpayload.setEmail(Email);
		userpayload.setPassword(Password);
		userpayload.setPhone(Phone);
		userpayload.setUserStatus(Integer.valueOf(UserStatus));

		UserEndPoints2.createUser(userpayload); // Pre-requiste for Delete user is to have one user created.

		Response response = UserEndPoints2.deleteUser(this.userpayload.getUsername());

		assertThat(response.getHeader("access-control-allow-headers"), is("Content-Type, api_key, Authorization"));
		assertThat(response.getHeader("Access-Control-Allow-Methods"), is("GET, POST, DELETE, PUT"));
	}

	@AfterTest
	public void Teardown() {

		System.out.println("Execution completed");
	}

}
