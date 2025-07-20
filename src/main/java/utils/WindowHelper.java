package utils;

import org.openqa.selenium.WebDriver;

import java.util.Set;

public class WindowHelper {

    public void switchToChildWindow(WebDriver driver)
    {
        String parentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }
}
