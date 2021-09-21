package deliveryConfigurationSanity.step_def;

import deliveryConfigurationSanity.utilities.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {


    @Before
    public void setup(){

        DriverFactory.getDriver().manage().window().maximize();
        DriverFactory.getDriver().manage().deleteAllCookies();


    }

    @After
    public void tearDown(Scenario scenario) {
        //how to check if scenario failed

        if (scenario.isFailed()) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) DriverFactory.getDriver();
            byte[] image = takesScreenshot.getScreenshotAs(OutputType.BYTES);

            //attach screenshot to the report
            scenario.embed(image, "image/png", scenario.getName());


        }

        // DriverFactory.closeDriver();

    }


    }
