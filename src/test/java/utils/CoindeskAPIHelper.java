package utils;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CoindeskAPIHelper {

    private static final String BASE_URL = "https://api.coindesk.com/v1/bpi/currentprice.json";
    private Response response;

    public void sendGetRequest() {
        response = given()
                .when()
                .get(BASE_URL)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    public Response getResponse() {
        return response;
    }
}
