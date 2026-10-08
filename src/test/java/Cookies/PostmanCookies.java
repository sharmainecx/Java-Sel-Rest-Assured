package Cookies;

import io.restassured.http.Cookie;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostmanCookies {


    private static final String BASE_URI = "https://postman-echo.com";
    private static final String ENDPOINT = "/basic-auth";

    private Response callBasicAuth() {
        return given()
                .baseUri(BASE_URI)
                .auth().preemptive().basic("postman", "password")
                .log().uri()
                .when()
                .get(ENDPOINT);
    }

    @Test
    public void verifyPostmanSailsIDCookies(){

        Response response = callBasicAuth();
        Assert.assertEquals(response.statusCode(), 200);

        Cookie sid = response.detailedCookie("sails.sid");

        Assert.assertNotNull(sid, "sails.sid cookie is not set");
        Assert.assertFalse(sid.getValue().isEmpty(), "sails.sid has Value");
        Assert.assertEquals(sid.getPath(), "/");
        Assert.assertNull(sid.getExpiryDate(), "sails.sid has an expiry date");
        Assert.assertTrue(sid.isHttpOnly(), "sails.sid is not HTTP only");
        Assert.assertFalse(sid.isSecured(), "said sid is secured");
        Assert.assertNull(sid.getDomain(),"there's a domain response from Postman");
    }

    @Test
    public void verifyPostmanCloudflareCookies()
    {
        Response response = callBasicAuth();
        Assert.assertEquals(response.statusCode(), 200);

        for(String s : new String[]{"__cf_bm", "_cfuvid"}){
            Cookie c = response.detailedCookie(s);

            Assert.assertNotNull(c);
            Assert.assertFalse(c.getValue().isEmpty(), c+"value is empty");
            Assert.assertEquals(c.getDomain(), "postman-echo.com");
            Assert.assertEquals(c.getPath(), "/");
            Assert.assertTrue(c.isHttpOnly());
            Assert.assertTrue(c.isSecured());

            if(s.equals("__cf_bm")){
                Assert.assertNotNull(c.getExpiryDate());
            }
            else {
                Assert.assertNull(c.getExpiryDate());
            }
        }
    }
}


