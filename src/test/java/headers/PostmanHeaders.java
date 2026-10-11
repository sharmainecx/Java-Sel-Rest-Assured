package headers;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostmanHeaders {

    public static final String BASE_URI = "https://postman-echo.com";
    public static final String ENDPOINT = "/basic-auth";

    @BeforeClass
    public Response testPostmanHeaders(){

        return given()
                .baseUri(BASE_URI)
                .auth().preemptive().basic("postman", "password")
                .log().uri()
                .when()
                .get(ENDPOINT);

    }

    @Test
    public void validateHeaders()
    {

        Response response = testPostmanHeaders();

        Assert.assertTrue(response.getHeaders().hasHeaderWithName("Date"));
        Assert.assertTrue(response.getHeaders().hasHeaderWithName("Content-Type"));
        Assert.assertTrue(response.getHeaders().hasHeaderWithName("Etag"));
        Assert.assertNotNull(response.getHeader("Content-Type"));
        Assert.assertEquals(response.getHeader("Content-Type"), "application/json; charset=utf-8");

        List<String> cookie = response.getHeaders().getValues("Set-Cookie");
        Assert.assertFalse(cookie.isEmpty());
        Assert.assertFalse(response.getHeaders().hasHeaderWithName("X-Powered-By"));
    }
}
