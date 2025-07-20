package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;
import utils.WindowHelper;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class EbaySteps {
    WebDriver driver;
    HomePage homePage;
    SearchResultsPage resultsPage;
    ItemPage itemPage;
    CartPage cartPage;
    WindowHelper helper;

    @Before
    public void setup() {
        helper = new WindowHelper();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Given("I open the eBay website")
    public void i_open_the_ebay_website() {
        driver.get("https://www.ebay.com");
        homePage = new HomePage(driver);
    }

    @When("I search for {string}")
    public void i_search_for(String query) {
        homePage.search(query);
        resultsPage = new SearchResultsPage(driver);
    }

    @When("I click on the first book in the list")
    public void i_click_on_the_first_book_in_the_list() {
        resultsPage.clickFirstBook();
        itemPage = new ItemPage(driver);
    }

    @When("I add the book to the cart")
    public void i_add_the_book_to_the_cart() {
        helper.switchToChildWindow(driver);
        itemPage.addToCart();
        cartPage = new CartPage(driver);
    }

    @Then("I should see the cart updated with the correct number of items")
    public void i_should_see_the_cart_updated() {
        cartPage.clickDialog();
        String count = cartPage.getCartCount();
        System.out.println("Cart Count: " + count);
        assertTrue("Cart count should not be empty", !count.isEmpty());
        assertEquals("1", count);
    }

    @After
    public void tearDown() {
//        driver.quit();
    }
}
