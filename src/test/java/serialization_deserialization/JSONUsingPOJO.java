package serialization_deserialization;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import pojo.Api;
import pojo.GetCourse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class JSONUsingPOJO {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String[] courseTitles = {"Selenium Webdriver Java", "Cypress", "Protractor"};

        String response = 	given()
                .formParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .formParams("grant_type","client_credentials")
                .formParams("scope","trust")
                .when().log().all()
                .post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();

 //       System.out.println(response);
        JsonPath jsonPath = new JsonPath(response);
        String accessToken = jsonPath.getString("access_token");


        GetCourse gc=	given()
                .queryParams("access_token", accessToken)
                .when().log().all()
                .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);

  //      System.out.println(gc);

        System.out.println(gc.getLinkedIn()+" 32 ");
        System.out.println(gc.getInstructor()+" 33 ");

        //print Price of All API Courses
        System.out.println(gc.getCourses().getApi().get(0).getCourseTitle());
        System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());

        //Print Price of only Soap API Course

        List<Api> apiCourse = gc.getCourses().getApi();

        for(int i=0;i<apiCourse.size();i++)
        {
            if(apiCourse.get(i).getCourseTitle().equalsIgnoreCase("Rest Assured Automation using Java"))
            {
                System.out.println(apiCourse.get(i).getPrice());
            }
        }

        //Get the course names of WebAutomation
        ArrayList<String> a= new ArrayList<String>();


        List<pojo.WebAutomation> w=gc.getCourses().getWebAutomation();

        for(int j=0;j<w.size();j++)
        {
            a.add(w.get(j).getCourseTitle());
        }

        List<String> expectedList=	Arrays.asList(courseTitles);

        Assert.assertTrue(a.equals(expectedList));
    }
}
