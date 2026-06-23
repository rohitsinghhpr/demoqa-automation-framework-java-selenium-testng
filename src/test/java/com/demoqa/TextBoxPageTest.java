package com.demoqa;

import org.testng.Assert;
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
        textBoxPage.enterEmail(email);
        textBoxPage.enterCurrentAddress(currentAddress);
        textBoxPage.enterPermanentAddress(permanentAddress);

        textBoxPage.clickOnSubmitButton();

        Assert.assertTrue(textBoxPage.getFullNameOutputText().contains(fullName));
        Assert.assertTrue(textBoxPage.getEmailOutputText().contains(email));
        Assert.assertTrue(textBoxPage.getCurrentAddressOutputText().contains(currentAddress));
        Assert.assertTrue(textBoxPage.getPermanentAddressOutputText().contains(permanentAddress));

    }
}
