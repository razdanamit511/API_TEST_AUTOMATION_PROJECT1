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

public class postTests {

	Faker faker; // This class helps us to generate fake dummy data to used for API payloads.
	User userpayload;

	/* -------------------- POST API - TESTS ----------------------- */
	@Test(description = "Validate that API should return API status code within 2XX series", dataProvider = "alldata", dataProviderClass = DataProviders.class, priority = 1)
	public void PostUserTestMethod1(String UserId, String UserName, String FIrstName, String LastName, String Email, String Password, String Phone, String UserStatus) throws IOException {

		userpayload = new User();
		
		userpayload.setId(Integer.valueOf(UserId));
		userpayload.setUsername(UserName);
		userpayload.setFirstName(FIrstName);
		userpayload.setLastName(LastName);
		userpayload.setEmail(Email);
		userpayload.setPassword(Password);
		userpayload.setPhone(Phone);
		userpayload.setUserStatus(Integer.valueOf(UserStatus));
		
		Response response = UserEndPoints2.createUser(userpayload);
		Matcher<String> matcher1 = matchesPattern("^20[0-9]$");

		System.out.println("Response : " + response.asPrettyString() + " username :" + this.userpayload.getUsername());
		assertThat("Assertion 1", matcher1.matches(String.valueOf(response.getStatusCode())));

	}

	@Test(description = "Validate that API should return API status code as 200", dataProvider = "alldata", dataProviderClass = DataProviders.class, priority = 2)
	public void PostUserTestMethod2(String UserId, String UserName, String FIrstName, String LastName, String Email, String Password, String Phone, String UserStatus) throws IOException {

		userpayload = new User();
		
		userpayload.setId(Integer.valueOf(UserId));
		userpayload.setUsername(UserName);
		userpayload.setFirstName(FIrstName);
		userpayload.setLastName(LastName);
		userpayload.setEmail(Email);
		userpayload.setPassword(Password);
		userpayload.setPhone(Phone);
		userpayload.setUserStatus(Integer.valueOf(UserStatus));
		
		Response response = UserEndPoints2.createUser(userpayload);
		assertThat(response.getStatusCode(), is(200));

	}

	@Test(description = "Validate that API should return a response payload ie. Non-empty response body and response header", dataProvider = "alldata", dataProviderClass = DataProviders.class, priority = 3)
	public void PostUserTestMethod3(String UserId, String UserName, String FIrstName, String LastName, String Email, String Password, String Phone, String UserStatus) throws IOException {

		userpayload = new User();
		
		userpayload.setId(Integer.valueOf(UserId));
		userpayload.setUsername(UserName);
		userpayload.setFirstName(FIrstName);
		userpayload.setLastName(LastName);
		userpayload.setEmail(Email);
		userpayload.setPassword(Password);
		userpayload.setPhone(Phone);
		userpayload.setUserStatus(Integer.valueOf(UserStatus));
		
		Response response = UserEndPoints2.createUser(userpayload);

		String responseBody = response.getBody().toString();
		assertThat(responseBody, not(isEmptyString()));

		String responseHeaders = response.getHeaders().toString();
		assertThat(responseHeaders, not(isEmptyString()));

	}

	@Test(description = "Validate that API should return response of type = JSON", dataProvider = "alldata", dataProviderClass = DataProviders.class, priority = 4)
	public void PostUserTestMethod4(String UserId, String UserName, String FIrstName, String LastName, String Email, String Password, String Phone, String UserStatus) throws IOException {

		
		userpayload = new User();
		
		userpayload.setId(Integer.valueOf(UserId));
		userpayload.setUsername(UserName);
		userpayload.setFirstName(FIrstName);
		userpayload.setLastName(LastName);
		userpayload.setEmail(Email);
		userpayload.setPassword(Password);
		userpayload.setPhone(Phone);
		userpayload.setUserStatus(Integer.valueOf(UserStatus));
		
		
		Response response = UserEndPoints2.createUser(userpayload);

		assertThat(response.getContentType(), is("application/json"));

	}

	@Test(description = "Validate that API should should adhere to Response structure as per data model mentioned in API Spec or API Doc.- JSON SCHEMA VALIDATION", dataProvider = "alldata", dataProviderClass = DataProviders.class, priority = 5)
	public void PostUserTestMethod5(String UserId, String UserName, String FIrstName, String LastName, String Email, String Password, String Phone, String UserStatus) throws IOException {

		
		userpayload = new User();
		
		userpayload.setId(Integer.valueOf(UserId));
		userpayload.setUsername(UserName);
		userpayload.setFirstName(FIrstName);
		userpayload.setLastName(LastName);
		userpayload.setEmail(Email);
		userpayload.setPassword(Password);
		userpayload.setPhone(Phone);
		userpayload.setUserStatus(Integer.valueOf(UserStatus));
		
		
		Response response = UserEndPoints2.createUser(userpayload);
		// JsonSchemaValidator validator =
		// JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonschemaval.json");

		/*
		 * String str="{\r\n" + "    \"code\": 200,\r\n" +
		 * "    \"type\": \"unknown\",\r\n" + "    \"message\": \"1231838\"\r\n" + "}";
		 */

		assertThat(response.jsonPath().prettyPrint(),
				JsonSchemaValidator.matchesJsonSchemaInClasspath("User\\Create_jsonschemaval.json"));
	}

	@Test(description = "Valdiate that API should return response body as per the req spec", dataProvider = "alldata", dataProviderClass = DataProviders.class, priority = 6)
	public void PostUserTestMethod6(String UserId, String UserName, String FIrstName, String LastName, String Email, String Password, String Phone, String UserStatus) throws IOException {

		userpayload = new User();
		
		userpayload.setId(Integer.valueOf(UserId));
		userpayload.setUsername(UserName);
		userpayload.setFirstName(FIrstName);
		userpayload.setLastName(LastName);
		userpayload.setEmail(Email);
		userpayload.setPassword(Password);
		userpayload.setPhone(Phone);
		userpayload.setUserStatus(Integer.valueOf(UserStatus));
		
		
		Response response = UserEndPoints2.createUser(userpayload);

		assertThat(response.body().jsonPath().get("code"), is(200));
		assertThat(response.body().jsonPath().get("type"), is("unknown"));

//		System.out.println("Message :"+response.body().jsonPath().get("message"));

		assertThat(response.body().jsonPath().get("message").toString().length(), is(greaterThan(6)));
	}

	@Test(description = "Valdiate that API should return response headers as per the req spec", dataProvider = "alldata", dataProviderClass = DataProviders.class, priority = 7)
	public void PostUserTestMethod7(String UserId, String UserName, String FIrstName, String LastName, String Email, String Password, String Phone, String UserStatus) throws IOException {

		userpayload = new User();
		
		userpayload.setId(Integer.valueOf(UserId));
		userpayload.setUsername(UserName);
		userpayload.setFirstName(FIrstName);
		userpayload.setLastName(LastName);
		userpayload.setEmail(Email);
		userpayload.setPassword(Password);
		userpayload.setPhone(Phone);
		userpayload.setUserStatus(Integer.valueOf(UserStatus));
		
		Response response = UserEndPoints2.createUser(userpayload);

		Headers headers = response.getHeaders();

		assertThat("Contains required headers", headers.hasHeaderWithName("Content-type"));

		assertThat(response.getHeader("Content-type"), is("application/json"));
	}


}
