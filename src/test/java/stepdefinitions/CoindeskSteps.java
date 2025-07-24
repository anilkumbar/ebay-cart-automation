package stepdefinitions;

import io.cucumber.java.en.And;
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

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer expectedStatusCode) {
        Assert.assertEquals(expectedStatusCode.intValue(), response.getStatusCode());
    }

    @And("the response should contain prices for USD, GBP, and EUR")
    public void the_response_should_contain_prices_for_usd_gbp_and_eur() {

        // Verify there are 3 BPIs - USD, GBP, EUR
        Assert.assertNotNull("USD price is missing", response.jsonPath().getString("market_data.current_price.usd"));
        Assert.assertNotNull("GBP price is missing", response.jsonPath().getString("market_data.current_price.gbp"));
        Assert.assertNotNull("EUR price is missing", response.jsonPath().getString("market_data.current_price.eur"));


        //Verify price change percentage over last 24 hours
        Double priceChange24h = response.jsonPath().getDouble("market_data.price_change_percentage_24h");
        Assert.assertNotNull("Price change percentage 24h is missing", priceChange24h);

        //Verify homepage URL is not empty
        String homepage = response.jsonPath().getString("links.homepage[0]");
        Assert.assertNotNull("Homepage URL is missing", homepage);
        Assert.assertFalse("Homepage URL is empty", homepage.trim().isEmpty());

        System.out.println("All checks passed successfully!");
    }

    @And("each currency should have a market cap and total volume")
    public void each_currency_should_have_a_market_cap_and_total_volume() {
        //Verify market cap and total volume for each
        Assert.assertNotNull("USD market cap is missing", response.jsonPath().getString("market_data.market_cap.usd"));
        Assert.assertNotNull("GBP market cap is missing", response.jsonPath().getString("market_data.market_cap.gbp"));
        Assert.assertNotNull("EUR market cap is missing", response.jsonPath().getString("market_data.market_cap.eur"));

        Assert.assertNotNull("USD total volume is missing", response.jsonPath().getString("market_data.total_volume.usd"));
        Assert.assertNotNull("GBP total volume is missing", response.jsonPath().getString("market_data.total_volume.gbp"));
        Assert.assertNotNull("EUR total volume is missing", response.jsonPath().getString("market_data.total_volume.eur"));

    }

    @Then("the price change percentage over the last 24 hours should be present")
    public void the_price_change_percentage_over_the_last_24_hours_should_be_present() {
        Assert.assertNotNull(response.jsonPath().getDouble("market_data.price_change_percentage_24h"));
    }

    @Then("the homepage URL should not be empty")
    public void the_homepage_url_should_not_be_empty() {
        Assert.assertNotNull(response.jsonPath().getString("links.homepage[0]"));
    }
}

