package Reqres.inAPIs;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PutRequest {

    @Test()
    public void putRequest()
    {
        HashMap<String, String> hm= new HashMap<>();
        hm.put("name", "James");
        hm.put("job", "still unemployed");


        Response response =
        given()
                .contentType("application/json")
                .body(hm)
                .when()
                .put("https://reqres.in/api/users/2");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");

        String name = response.jsonPath().getString("name");
        String job = response.jsonPath().getString("job");

        Assert.assertEquals(hm.get("name"), name);
        Assert.assertEquals(hm.get("job"), job);
    }
}
