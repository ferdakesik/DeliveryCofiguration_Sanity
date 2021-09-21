package deliveryConfigurationSanity.pages;

import deliveryConfigurationSanity.utilities.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {



    private static final Logger logger= LogManager.getLogger(BasePage.class);

    public BasePage() {

        PageFactory.initElements(DriverFactory.getDriver(),this);

    }



}
