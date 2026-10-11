package schemas;

import utils.PayloadReader;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class JSONSchema {

    private static String BASE_URI = "https://petstore.swagger.io";
    private static String ENDPOINT = "/v2/store/order";

    @Test
    public void getResponse() throws IOException {
       given()
                .baseUri(BASE_URI)
                .contentType("application/json")
                .accept("application/json")
                .body(PayloadReader.getPayload("src/test/resources/payloads/newdog.json"))
                .when()
                .post(ENDPOINT)
                .then()
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/dogschema.json"));
    }
}
