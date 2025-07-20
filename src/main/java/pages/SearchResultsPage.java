package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultsPage {
    WebDriver driver;

    By firstBook = By.xpath("(//li[starts-with(@id,'item')]/descendant::img)[1]"); // Adjust if needed

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFirstBook() {
        driver.findElements(firstBook).get(0).click();
    }
}
