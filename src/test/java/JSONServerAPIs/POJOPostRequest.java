package JSONServerAPIs;

import POJO.PostRequest;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class POJOPostRequest {

    PostRequest preq = new PostRequest();

    @Test
    public void createUser()
    {
        preq.setName("Clark Kent");
        preq.setGrade("12th");
        preq.setAge(20);

        String[] lang = {"English", "Math", "Humanities"};
        preq.setSubjects(lang);

        Response response =
        given()
                .contentType("application/json")
                .body(preq)
                .when()
                .post("http://localhost:3000/students");

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.contentType(), "application/json");
        Assert.assertFalse(response.getBody().asString().isEmpty());

        String name = response.jsonPath().getString("name");
        int age = response.jsonPath().getInt("age");
        String grade = response.jsonPath().getString("grade");

        List<String> resList = response.jsonPath().getList("subjects", String.class);
        String[] expected = (String[])preq.getSubjects();

        Assert.assertEquals(preq.getName(), name);
        Assert.assertEquals(preq.getAge(),age);
        Assert.assertEquals(resList, Arrays.asList(expected));

    }
}
