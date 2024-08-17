package testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.ReusableMethods;
import data.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class LibraryAPI {

	@Test(dataProvider="BookData", priority = 0)
	public void addBook(String isbn, String aisle) {
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().log().all().header("Content-Type", "application/json")
		.body(payload.AddBook(isbn,aisle))
		.when().post("/Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = ReusableMethods.rawToJson(response);
		String id = js.get("ID");
		System.out.println(id);

	}
	
	@Test(dataProvider="BookData", priority = 1)
	public void deleteBook(String isbn, String aisle) {
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().log().all().header("Content-Type", "application/json")
		.body("{\r\n"
				+ " \r\n"
				+ "\"ID\" : \""+isbn+aisle+"\"\r\n"
				+ " \r\n"
				+ "} ")
		.when().delete("/Library/DeleteBook.php")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = ReusableMethods.rawToJson(response);
		String msg = js.get("msg");
		System.out.println(msg);

	}
	
	@DataProvider(name="BookData")
	public Object[][] getData() {
		
		return new Object[][] {{"qwerty","123"},{"qwerty","1234"},{"qwerty","12345"}};
	}

}
