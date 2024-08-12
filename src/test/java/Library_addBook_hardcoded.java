import files.Payload;
import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class Library_addBook_hardcoded {

    @Test
    public void addBook()
    {
        RestAssured.baseURI = "http://216.10.245.166";

Response resp =
        given().
                header("Content-Type","application/json").
                body(Payload.addBook()).
        when().
                post("Library/Addbook.php").
                then().assertThat().
                                    statusCode(200).
                                    extract().response();

        JsonPath js = ReUsableMethods.rawToJson(resp.toString());

    //    String id = (js.get("ID"));

        System.out.println(js);
    }
}
