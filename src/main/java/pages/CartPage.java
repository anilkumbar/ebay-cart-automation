package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class CartPage {
    WebDriver driver;

    By cartCount = By.xpath("//span[@class='gh-cart__icon']/span");
    By dialogClose = By.xpath("//button[@class='icon-btn lightbox-dialog__close' and @aria-label='Close overlay']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getCartCount() {

        return driver.findElement(cartCount).getText();
    }

    public void clickDialog() {
        driver.findElement(dialogClose).click();
    }
}
