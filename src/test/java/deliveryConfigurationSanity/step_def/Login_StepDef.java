package deliveryConfigurationSanity.step_def;

import deliveryConfigurationSanity.pages.Login;
import deliveryConfigurationSanity.utilities.BrowserUtils;
import deliveryConfigurationSanity.utilities.ConfigurationReader;
import deliveryConfigurationSanity.utilities.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class Login_StepDef {

    Login login = new Login();

    @Given("I am on the website")
    public void i_am_on_the_website() {
        DriverFactory.getDriver().get(ConfigurationReader.getProperty("url"));

    }

    @When("I enter the credentials")
    public void i_enter_the_credentials() throws Exception {
        BrowserUtils.wait(5);
        Thread.sleep(5000);
        login.SetLogin();
    }

    @Then("I should be able to login main website page")
    public void i_should_be_able_to_login_main_website_page() throws InterruptedException {
//  String expectedTitle="Client Center";
        // BrowserUtils.waitVisibilityOf(DriverFactory.getDriver().findElement(By.xpath("//title")));
        Thread.sleep(5000);
        BrowserUtils.wait(10);
        Assert.assertEquals("Title verification is failed!!!", "Client Center", DriverFactory.getDriver().getTitle());
        // assertEquals(message(optional)String, expected, actual);
    }


}
