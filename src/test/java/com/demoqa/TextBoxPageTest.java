package com.demoqa;

import listeners.ListenerExtentReport;
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
        ListenerExtentReport.getExtentTest().info("Full Name entered: " + fullName);
        textBoxPage.enterEmail(email);
        ListenerExtentReport.getExtentTest().info("Email entered: " + email);
        textBoxPage.enterCurrentAddress(currentAddress);
        ListenerExtentReport.getExtentTest().info("Current Address entered: " + currentAddress);
        textBoxPage.enterPermanentAddress(permanentAddress);
        ListenerExtentReport.getExtentTest().info("Permanent Address entered: " + permanentAddress);

    }
}
