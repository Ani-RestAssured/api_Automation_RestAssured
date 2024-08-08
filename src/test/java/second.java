import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class second {

    public static void main(String[] args)
    {
        RestAssured.baseURI="https://rahulshettyacademy.com";

                String response = given().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(Payload.AddPlace()).
                when().post("/maps/api/place/add/json").
                then().assertThat().
                                  statusCode(200).
                                  body("scope",equalTo("APP")).
                                  header("server","Apache/2.4.52 (Ubuntu)").extract().asString();


        System.out.println("Response "+ response);

        JsonPath js =new JsonPath(response); //for Parsing JSON
        String placeId = js.getString("place_id");
        System.out.println(placeId);
    }
}
