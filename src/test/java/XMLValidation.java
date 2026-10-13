import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class XMLValidation {

    private static String BASE_URI = "https://petstore.swagger.io";
    private static String ENDPOINT = "/v2/pet/findByStatus";

    @BeforeTest
    public Response getPetByStatus(){
        return given()
                .baseUri(BASE_URI)
                .accept(ContentType.XML)
                .queryParam("status", "available")
                .when()
                .get(ENDPOINT);
    }

    @Test
    public void getPetDetails(){
        Response response = getPetByStatus();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.getHeaders().hasHeaderWithName("Content-Type"));
        Assert.assertEquals(response.getHeader("Content-Type"), "application/xml");

        Assert.assertEquals(response.xmlPath().get("pets.Pet[0].name").toString(), "doggie");
    }

}
