import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class E2E {

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
        String placeId = js.getString("place_id");  // Can use this in subsequent requests
        System.out.println(placeId+"--placeID--");       // Can Use this in subsequent request

        String newAddress = "70 winter walk, USA";


//put operation using placeid variable recieved in previous example
        RestAssured.baseURI="http://rahulshettyacademy.com";
        given().log().all().queryParam("key","qaclick123").queryParam("place_id",placeId)
                           .header("Content-Type","application/json")
                .body("{\n" +
                        "\"place_id\":\""+placeId+"\",\n" +
                        "\"address\":\""+newAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}").
                when().put("/maps/api/place/update/json").
                then().assertThat().
                                    statusCode(200).
                                    body("msg",equalTo("Address successfully updated"));


//get operation using placeid variable recieved in previous example
        RestAssured.baseURI="http://rahulshettyacademy.com";
   String getPlaceResponse = given().log().all().queryParam("key","qaclick123").queryParam("place_id",placeId).
                             when().get("/maps/api/place/get/json").
                             then().assertThat().
                                                  statusCode(200).
                                                  extract().response().asString();

   JsonPath js2 = new JsonPath(getPlaceResponse);
   String updatedAddress = js2.getString("address");

        Assert.assertEquals(newAddress,updatedAddress);
    }
}
