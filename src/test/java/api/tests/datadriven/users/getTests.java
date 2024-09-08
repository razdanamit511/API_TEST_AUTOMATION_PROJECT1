package api.tests.datadriven.users;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.CustomAttribute;
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

import api.endpoints.UserEndPoints2;
import api.payload.User;
import api.utils.DataProviders;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class getTests {

	User userpayload;

	/* -------------------- GET API - TESTS ----------------------- */
	@Test(description = "Validate that API should return API status code within 2XX series", dataProvider = "alldata", dataProviderClass = DataProviders.class, groups = {
			"GET API" }, priority = 1)
	@CustomAttribute(name = "Author", values = "Amit")
	public void GetUserTestMethod1(String UserId, String UserName, String FIrstName, String LastName, String Email,
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

		Response response = UserEndPoints2.getUser(this.userpayload.getUsername());

		System.out.println("Username : " + this.userpayload.getUsername());

		Matcher<String> matcher1 = matchesPattern("^20[0-9]$");
		assertThat("Assertion 1", matcher1.matches(String.valueOf(response.getStatusCode())));
	}

	@Test(description = "Validate that API should return API status code as 200", dataProvider = "alldata", dataProviderClass = DataProviders.class, groups = {
			"GET API" }, priority = 2)
	public void GetUserTestMethod2(String UserId, String UserName, String FIrstName, String LastName, String Email,
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

		Response response = UserEndPoints2.getUser(this.userpayload.getUsername());
		assertThat(response.getStatusCode(), is(200));

	}

	@Test(description = "Validate that API should return a response payload ie. Non-empty response body and response header", dataProvider = "alldata", dataProviderClass = DataProviders.class, groups = {
			"GET API" }, priority = 3)
	public void GetUserTestMethod3(String UserId, String UserName, String FIrstName, String LastName, String Email,
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

		Response response = UserEndPoints2.getUser(this.userpayload.getUsername());

		String responseBody = response.getBody().toString();
		assertThat(responseBody, not(isEmptyString()));

		String responseHeaders = response.getHeaders().toString();
		assertThat(responseHeaders, not(isEmptyString()));

	}

	@Test(description = "Validate that API should return response of type = JSON", dataProvider = "alldata", dataProviderClass = DataProviders.class, groups = {
			"GET API" }, priority = 4)
	public void GetUserTestMethod4(String UserId, String UserName, String FIrstName, String LastName, String Email,
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

		Response response = UserEndPoints2.getUser(this.userpayload.getUsername());

		assertThat(response.getContentType(), is("application/json"));

	}

	@Test(description = "Validate that API should should adhere to Response structure as per data model mentioned in API Spec or API Doc.- JSON SCHEMA VALIDATION", dataProvider = "alldata", dataProviderClass = DataProviders.class, groups = {
			"GET API" }, priority = 5)
	public void GetUserTestMethod5(String UserId, String UserName, String FIrstName, String LastName, String Email,
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

		Response response = UserEndPoints2.getUser(this.userpayload.getUsername());

		assertThat(response.jsonPath().prettyPrint(),
				JsonSchemaValidator.matchesJsonSchemaInClasspath("User\\Get_jsonschemaval.json"));

	}

	@Test(description = "Valdiate that API should return response body with details of all existing users", dataProvider = "alldata", dataProviderClass = DataProviders.class, groups = {
			"GET API" }, priority = 6)
	public void GetUserTestMethod6(String UserId, String UserName, String FIrstName, String LastName, String Email,
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

		Response response = UserEndPoints2.getUser(this.userpayload.getUsername());

		assertThat(response.getBody().jsonPath().get("id"), is(this.userpayload.getId()));
		assertThat(response.getBody().jsonPath().get("username"), is(this.userpayload.getUsername()));
		assertThat(response.getBody().jsonPath().get("firstName"), is(this.userpayload.getFirstName()));
		assertThat(response.getBody().jsonPath().get("lastName"), is(this.userpayload.getLastName()));
		assertThat(response.getBody().jsonPath().get("email"), is(this.userpayload.getEmail()));
		assertThat(response.getBody().jsonPath().get("password"), is(this.userpayload.getPassword()));
		assertThat(response.getBody().jsonPath().get("phone"), is(this.userpayload.getPhone()));
		assertThat(response.getBody().jsonPath().get("userStatus"), is(this.userpayload.getUserStatus()));

	}

	@Test(description = "Valdiate that API should return response headers as per the spec", dataProvider = "alldata", dataProviderClass = DataProviders.class, groups = {
			"GET API" }, priority = 7)
	public void GetUserTestMethod7(String UserId, String UserName, String FIrstName, String LastName, String Email,
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

		Response response = UserEndPoints2.getUser(this.userpayload.getUsername());

		assertThat(response.getHeader("access-control-allow-headers"), is("Content-Type, api_key, Authorization"));
		assertThat(response.getHeader("Access-Control-Allow-Methods"), is("GET, POST, DELETE, PUT"));
	}

}
