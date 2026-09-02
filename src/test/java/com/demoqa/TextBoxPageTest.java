package com.demoqa;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.FrameworkConstants;
import utils.WebDriverUtils;

public class TextBoxPageTest extends BaseTest{
    @BeforeClass
    public void openBrowser(){
        setUpWebDriver(FrameworkConstants.BROWSER);
    }
    @AfterClass
    public void closeBrowser(){
        tearDownWebDriver();
    }
    @Test
    public void verifyTextBoxFormSubmission() {

        String fullName = "John Doe";
        String email = "john.doe@test.com";
        String currentAddress = "Current Address";
        String permanentAddress = "Permanent Address";

        TextBoxPage textBoxPage = new TextBoxPage(WebDriverUtils.getDriver());

        textBoxPage.enterFullName(fullName);
        System.out.println("Full Name entered: " + fullName);
        textBoxPage.enterEmail(email);
        System.out.println("Email entered: " + email);
        textBoxPage.enterCurrentAddress(currentAddress);
        System.out.println("Current Address entered: " + currentAddress);
        textBoxPage.enterPermanentAddress(permanentAddress);
        System.out.println("Permanent Address entered: " + permanentAddress);

    }
}
