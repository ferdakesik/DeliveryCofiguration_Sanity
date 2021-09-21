package deliveryConfigurationSanity.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Logout extends BasePage{

    @FindBy(xpath="//button[contains(@class,'MuiButtonBase-root MuiIconButton-root')]")
    public WebElement Bbutton;


    @FindBy(xpath = "//a[contains(@class, 'MuiLink-underlineHover') and contains(@href, 'signout')]")
    public WebElement Signout;



}
