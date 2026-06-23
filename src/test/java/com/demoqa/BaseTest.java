package com.demoqa;

import org.openqa.selenium.WebDriver;
import utils.FrameworkConstants;
import utils.WebDriverUtils;

public class BaseTest {
    public void setUpWebDriver(String browserName){
        WebDriver driver = WebDriverUtils.createInstanceOfWebDriver(browserName);
        WebDriverUtils.setDriver(driver);
        WebDriverUtils.getDriver().manage().window().maximize();
        WebDriverUtils.getDriver().get(FrameworkConstants.BASE_URL);
    }
    public void tearDownWebDriver(){
        WebDriver driver = WebDriverUtils.getDriver();
        try {
            if (driver != null) {
                driver.quit();
            }
        } finally {
            WebDriverUtils.unload();
        }
    }
}
