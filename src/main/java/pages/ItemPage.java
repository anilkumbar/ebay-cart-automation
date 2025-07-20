package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ItemPage {
    WebDriver driver;

    By addToCartButton = By.id("atcBtn_btn_1");

    public ItemPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addToCart() {
        driver.findElement(addToCartButton).click();
    }
}
