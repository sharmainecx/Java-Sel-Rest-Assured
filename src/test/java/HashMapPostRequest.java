import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class HashMapPostRequest {

    @Test
    public void createPostRequestUsingHashMap(){

        HashMap<String, Object> hm = new HashMap<>();

        hm.put("name", "Barry Allen");
        hm.put("age", 20);
        hm.put("grade", "12th");

        String [] subjects = {"Math", "English", "Science"};
        hm.put("subjects", subjects);

        Response response =
        given()
                .contentType("application/json")
                .body(hm)
                .when()
                .post("http://localhost:3000/students");

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.getContentType(), "application/json");

        String id = response.jsonPath().getString("id");
        Assert.assertNotNull(id);

        String name = response.jsonPath().getString("name");
        int age = response.jsonPath().getInt("age");
        String grade = response.jsonPath().getString("grade");

        List<String> resSubjects = response.jsonPath().getList("subjects", String.class);
        String[] expected = (String[]) hm.get("subjects");

        Assert.assertEquals(resSubjects, Arrays.asList(expected));
        Assert.assertEquals(hm.get("name"), name);
        Assert.assertEquals(hm.get("age"), age);
        Assert.assertEquals(hm.get("grade"), grade);
    }
}
