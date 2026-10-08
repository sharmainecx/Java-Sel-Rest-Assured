package JSONServerAPIs;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.given;

public class ExternalJSONFilePostRequest {


    @Test
    public void createUser() throws FileNotFoundException {
        File f = new File ("src/test/java/Payload/body.json");
        FileReader fr = new FileReader(f);
        JSONTokener jt = new JSONTokener(fr);
        JSONObject data = new JSONObject(jt);

        Response response =
        given()
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post("http://localhost:3000/students");

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.getContentType(), "application/json");
        Assert.assertEquals(response.jsonPath().getString("name"), data.get("name"));
    }





}
