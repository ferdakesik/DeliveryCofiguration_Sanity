package deliveryConfigurationSanity.pages;

import deliveryConfigurationSanity.utilities.BrowserUtils;
import deliveryConfigurationSanity.utilities.ConfigurationReader;
import deliveryConfigurationSanity.utilities.EmailUtils;
import org.joda.time.LocalDateTime;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login extends BasePage {


    @FindBy(xpath = "//input[@class='form-control']")
    private WebElement userName;

    @FindBy(id="nextBtn")
    private WebElement NextButton;

    //b2c username page
    @FindBy(id = "rawUserInput")
    private WebElement b2c_userName;

    @FindBy(id = "continue")
    private WebElement b2c_NextButton;
    @FindBy(className = "confirmlogin-card")
    private WebElement unrecognized_ip_window;
    @FindBy(className = "card-message")
    private WebElement unrecognized_ip_message;
    @FindBy(id = "VerificationCode")
    private WebElement key_input_field;
    @FindBy(className = "verifyCode")
    private WebElement verify_code_btn;

    @FindBy(xpath = "//input[@name='passwd']")
    private WebElement password;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement sign;

    @FindBy(id = "idSIButton9")
    private WebElement StaySignedIn;


    public void SetLogin()throws Exception{
        EmailUtils emailUtils = new EmailUtils();
        String userNameValue = ConfigurationReader.getProperty("userNameValue");
        String passwordValue = ConfigurationReader.getProperty("passwordValue");
        try {
            userName.sendKeys(userNameValue);
            BrowserUtils.wait(7);
            NextButton.click();
            BrowserUtils.wait(7);
        } catch (Exception e) {
            b2c_userName.sendKeys(userNameValue);
            BrowserUtils.wait(7);
            b2c_NextButton.click();
            BrowserUtils.wait(7);
        }
        password.sendKeys(passwordValue);
        BrowserUtils.wait(7);
        sign.click();
        BrowserUtils.wait(7);
        StaySignedIn.click();

        LocalDateTime timeNow = LocalDateTime.now();

        System.out.println("Time Now: "+timeNow);
        BrowserUtils.wait(10);

        boolean needVerification = emailUtils.needToBeVerified();

        if (needVerification){
            BrowserUtils.waitVisibilityOf(unrecognized_ip_window);
            BrowserUtils.wait(5);
            String Code_verification_window_message = unrecognized_ip_message.getText();
            System.out.println("Code Verification Window Message: "+Code_verification_window_message);
            String securityKey = emailUtils.getSecurityKey(timeNow, "Bob.Backline@npd.com", "Baseball1", "Bob");
            BrowserUtils.wait(5);
            key_input_field.sendKeys(securityKey);
            BrowserUtils.wait(5);
            verify_code_btn.click();
            BrowserUtils.wait(5);
        }
    }
}

