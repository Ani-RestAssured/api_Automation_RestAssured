package library;

import files.Payload;
import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Library_addBook_softcoded {

    @Test
    public void addBook()
    {
        RestAssured.baseURI = "http://216.10.245.166";

        Response response =
                given().
                        header("Content-Type","application/json").
                        body(Payload.addBook("bookname","Isbn",7,"author")).
                        when().
                        post("Library/Addbook.php").
                        then().assertThat().
                        statusCode(200).
                        extract().response();

        JsonPath js = ReUsableMethods.rawToJson(response.toString());

     //   String id = (js.get("ID"));

        System.out.println(js);
    }
}
