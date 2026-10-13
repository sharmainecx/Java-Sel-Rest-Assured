package schemas;

import utils.PayloadReader;
import io.restassured.matcher.RestAssuredMatchers;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class XMLSchema {

    private static String BASE_URI = "https://petstore.swagger.io";
    private static String ENDPOINT = "/v2/store/order";

    @Test
    public void validateXMLSchema() throws IOException {
        given()
                .baseUri(BASE_URI)
                .contentType("application/xml")
                .accept("application/xml")
                .body(PayloadReader.getPayload("src/test/resources/payloads/newdog.xml"))
                .when()
                .post(ENDPOINT)
                .then()
                .assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("schemas/dogschema.xsd"))
                .statusCode(200);
    }
}
