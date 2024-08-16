package spec;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.location;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Serialization_SpecBuilder {

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

        RequestSpecification spec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
                                 addQueryParam("key","qaclick123").
                                 setContentType(ContentType.JSON).
                                 build();

        RequestSpecification res = given().spec(spec).body(p);

        ResponseSpecification respspec = new ResponseSpecBuilder().
                                             expectStatusCode(200).
                                             expectContentType(ContentType.JSON).
                                             build();
        Response response  = res.
                             when().post("/maps/api/place/add/json").
                             then().spec(respspec).extract().response();

        String responseString = response.asString();
        System.out.println(responseString);
    }
}
