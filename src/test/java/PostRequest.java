import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class PostRequest {

   int id;
    @Test
    public void createUser(){

        HashMap<String, String> hm = new HashMap<>();
        hm.put("name", "John");
        hm.put("job", "unemployed");


        Response response =
        given()
                .contentType("application/json")
                .body(hm)
                .when()
                .post("https://reqres.in/api/users");
//                .then()
//                .statusCode(201)
//                .body("name", equalTo("John"))
//                .body("job", equalTo("unemployed"))
//                .body("id", notNullValue());

        Assert.assertEquals(response.getStatusCode(), 201);

        id=response.jsonPath().getInt("id");
        String name = response.jsonPath().getString("name");
        String job = response.jsonPath().getString("job");

        Assert.assertNotNull(id);
        Assert.assertEquals(hm.get("name"), name);
        Assert.assertEquals(hm.get("job"), job);
        System.out.print(id);
    }

//    @Test(dependsOnMethods = "createUser")
//    public void putRequest()
//    {
//
//        System.out.print(id);
//        HashMap<String, String> hm= new HashMap<>();
//        hm.put("name", "John");
//        hm.put("job", "still unemployed");
//
//
//        Response response =
//        given()
//                .contentType("application/json")
//                .when()
//                .get("https://reqres.in/api/users/"+id);
//
//        Assert.assertEquals(response.statusCode(),200);
//        Assert.assertEquals(response.contentType(), "application/json");
//        int resId = response.jsonPath().getInt("id");
//        String name = response.jsonPath().getString("name");
//        String job = response.jsonPath().getString("job");
//
//        Assert.assertEquals(resId,id);
//        Assert.assertEquals(hm.get("name"), name);
//        Assert.assertEquals(hm.get("job"), job);
//    }
}
