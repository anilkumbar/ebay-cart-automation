package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;
import utils.CoindeskAPIHelper;

public class CoindeskSteps {

    CoindeskAPIHelper apiHelper = new CoindeskAPIHelper();
    Response response;

    @Given("I send a GET request to Coindesk current price endpoint")
    public void i_send_get_request() {
        apiHelper.sendGetRequest();
        response = apiHelper.getResponse();
    }

    @Then("the response should contain 3 BPIs: USD, GBP, EUR")
    public void response_should_contain_3_bpis() {
        boolean hasUSD = response.jsonPath().get("bpi.USD") != null;
        boolean hasGBP = response.jsonPath().get("bpi.GBP") != null;
        boolean hasEUR = response.jsonPath().get("bpi.EUR") != null;

        Assert.assertTrue("USD is missing!", hasUSD);
        Assert.assertTrue("GBP is missing!", hasGBP);
        Assert.assertTrue("EUR is missing!", hasEUR);
    }

    @Then("the GBP description should be {string}")
    public void gbp_description_should_be(String expectedDescription) {
        String actualDescription = response.jsonPath().getString("bpi.GBP.description");
        Assert.assertEquals(expectedDescription, actualDescription);
    }
}

