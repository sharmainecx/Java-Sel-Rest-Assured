package JSONServerAPIs;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class OrgJSONLibraryPostRequest {

    @Test
    public void createUser() {
        JSONObject data = new JSONObject();

        data.put("name", "Barry Allen");
        data.put("age", 20);
        data.put("grade", "12th");

        String [] subjects = {"Math", "English", "Science"};
        data.put("subjects", subjects);

        Response response =
                given()
                        .contentType("application/json")
                        .body(data.toString())
                        .when()
                        .post("http://localhost:3000/students");

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.getContentType(), "application/json");
        Assert.assertFalse(response.getBody().toString().isEmpty());

        String name = response.jsonPath().getString("name");
        int age = response.jsonPath().getInt("age");
        String grade = response.jsonPath().getString("grade");

        List<String> resList = response.jsonPath().getList("subjects", String.class);
        String[] expected = (String[]) data.get("subjects");

        Assert.assertEquals(resList, Arrays.asList(expected));

    }
}
