import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import files.Payload;
public class first {

    public static void main(String[] args)
    {
        RestAssured.baseURI="https://rahulshettyacademy.com";

                String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(Payload.AddPlace()).
                when().post("/maps/api/place/add/json").
                then().log().all().assertThat().
                                  statusCode(200).
                                  body("scope",equalTo("APP")).
                                  header("server","Apache/2.4.52 (Ubuntu)").extract().asString();


        System.out.println("Response "+ response);

    }
}
