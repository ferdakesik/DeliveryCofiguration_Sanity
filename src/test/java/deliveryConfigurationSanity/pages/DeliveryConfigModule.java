package deliveryConfigurationSanity.pages;

import deliveryConfigurationSanity.utilities.BrowserUtils;
import deliveryConfigurationSanity.utilities.ConfigurationReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeliveryConfigModule extends BasePage {

   @FindBy(xpath = "/html/body/div/div/div[1]/div/div/div[2]/ul/li[5]/div/a/span[1]/div[2]")
    private WebElement DeliveryConfig;

   @FindBy(id = "basicSearch-Search")
   public WebElement searchBox;

   @FindBy(id = "searchIcon")
   public WebElement searchIcon;

   @FindBy(xpath = "(//input[@type='checkbox'])[1]")
   public WebElement firstCheckbox;

   @FindBy(xpath = "(//input[@type='checkbox'])[2]")
   public WebElement secondCheckbox;


    public void SetDelivery(){
        BrowserUtils.wait(10);
        DeliveryConfig.click();
        BrowserUtils.wait(15);


    }

    public void setDeliverySearch() {
        String searchBoxValue= ConfigurationReader.getProperty("searchBoxValue");
        searchBox.sendKeys(searchBoxValue);
        BrowserUtils.wait(10);
        searchIcon.click();
        BrowserUtils.wait(10);



    }
}


