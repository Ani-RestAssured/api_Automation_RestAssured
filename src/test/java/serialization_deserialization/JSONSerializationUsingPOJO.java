package serialization_deserialization;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import pojo.AddPlace;
import pojo.location;

import java.util.List;
import java.util.ArrayList;

public class JSONSerializationUsingPOJO {

    public static void main (String[] args)
    {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        AddPlace p = new AddPlace();
        p.setAccuracy(50);
        p.setName("Frontline house");
        p.setPhone_number("(+91) 983 893 3937");
        p.setAddress("29, side layout, cohen 09");
        p.setWebsite("http://google.com");
        p.setLanguage("French-IN");


        List<String> myList = new ArrayList<String>();
        myList.add(0,"shoe park");
        myList.add(1,"shop");
        p.setTypes(myList);

        location l = new location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);


        Response res = given().log().all().queryParam("key","qaclick123").
                body(p).
                when().post("/maps/api/place/add/json").
                then().assertThat().statusCode(200).extract().response();

        String responseString = res.asString();
        System.out.println(responseString);
    }
}
