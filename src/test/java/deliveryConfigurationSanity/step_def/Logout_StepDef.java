package deliveryConfigurationSanity.step_def;

import deliveryConfigurationSanity.pages.Logout;
import deliveryConfigurationSanity.utilities.BrowserUtils;
import deliveryConfigurationSanity.utilities.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class Logout_StepDef {

    Logout logout=new Logout();

    @When("I click the Icon on top right side")
    public void iClickTheIconOnTopRightSide() throws InterruptedException {
        Thread.sleep(5000);
        DriverFactory.getDriver().navigate().refresh();
        BrowserUtils.wait(10);
        logout.Bbutton.click();


    }


    @And("I should able to select {string}")
    public void iShouldAbleToSelect(String arg0) {
        BrowserUtils.wait(10);
        logout.Signout.click();


    }

    @Then("I should able to logout")
    public void iShouldAbleToLogout() {
        BrowserUtils.wait(10);
        String actualT=DriverFactory.getDriver().getTitle();
        String ExpectedT="Sign in";

        Assert.assertEquals(ExpectedT,actualT);
        BrowserUtils.wait(10);

        System.out.println("Actual Title is : " + actualT);
        System.out.println("Expected Title is : " + ExpectedT);

        if(actualT.equals(ExpectedT)){
            System.out.println("I already Logged out!!!");
        }else{
            System.out.println("Something is wrong, did not log out");
        }

    }

}

