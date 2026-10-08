package Reqres.inAPIs;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class DeleteRequest {

    @Test
    public void deleteRequest()
    {
        Response response =
               when()
                       .delete("https://reqres.in/api/users/2");

        Assert.assertEquals(response.statusCode(), 204);
        Assert.assertTrue(response.getBody().asString().isEmpty());
    }
}
