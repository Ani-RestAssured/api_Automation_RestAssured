import io.restassured.path.json.JsonPath;
import files.Payload;

public class ComplexJson {

    public static void main(String[] args)
    {
        JsonPath js = new JsonPath(Payload.mocker());

        // Get total number of courses
        int count = js.getInt("courses.size()");
        System.out.println(count);

        // Get Total Purchase Amount
        int pa = js.getInt("dashboard.purchaseAmount");
        System.out.println(pa);

        //Get Title of first course
        String courseTitle = js.getString("courses[0].title");
        System.out.println(courseTitle);

        //Get number of copies for RPA course
        int rpaCopies = js.getInt("courses[2].copies");
        System.out.println(rpaCopies);

        //Check if Total price matches total purchase amount
        int sum=0,sum1=0;
        for(int i=0;i<count;i++)
        {
          sum1 = sum1+(js.getInt("courses["+i+"].price")*(js.getInt("courses["+i+"].copies")));
          sum=sum+sum1;
        }

        System.out.println(sum);

        if(sum==pa)
        {
            System.out.println("Both are equal");
        }

        //Print copies of RPA. We first find RPA and then copies

        for(int i=0;i<count;i++)
        {
            if (js.getString("courses["+i+"].title").equalsIgnoreCase("rpa"))
            {
                System.out.println(js.getInt("courses["+i+"].price"));
                System.out.println(js.getInt("courses["+i+"].copies"));
            }
        }


    }
}
