package deliveryConfigurationSanity.step_def;
import deliveryConfigurationSanity.pages.DataBaseCon;
import deliveryConfigurationSanity.pages.DeliveryConfigModule;
import deliveryConfigurationSanity.utilities.BrowserUtils;
import deliveryConfigurationSanity.utilities.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


import java.util.List;

public class DeliveryConfig_StepDef {

    DeliveryConfigModule deliveryConfigModule=new DeliveryConfigModule();
    protected static DataBaseCon DBCon = new DataBaseCon();

    @Given("I am on the main page")
    public void iAmOnTheMainPage() {

    }

    @When("I clik the {string} Module")
    public void iClikTheModule(String arg0) {
        BrowserUtils.wait(5);
        deliveryConfigModule.SetDelivery();

    }

    @Then("I should navigate to the Module page")
    public void iShouldNavigateToTheModulePage() {
        BrowserUtils.wait(5);
        String expectedTitle="Immediate Delivery Configuration";
        String actualTitle= DriverFactory.getDriver().getTitle();
        Assert.assertEquals("Title",expectedTitle,actualTitle);

    }

    @And("I should be able to search {string} data on search column")
    public void iShouldBeAbleToSourceDataOnSearchColumn(String arg0) {
        BrowserUtils.wait(5);
     deliveryConfigModule.setDeliverySearch();



    }

    @Then("I should be able to check Run Daemon first box")
    public void iShouldBeAbleToCheckRunDaemonFirstBox() throws InterruptedException {
        BrowserUtils.wait(5);

        List<String> runDaemon = DBCon.readDataBasePDK("Select DISPLAY_NAME,RUN_DAEMON Module From NPDREPORTMANAGER_DQIBLD.TB_FF_MODULE_SUMMARY_SCHEMA WHERE DISPLAY_NAME='Canada Beauty POS Monthly - FF Profile'", "Module");
        System.out.println(runDaemon);
        System.out.println(runDaemon.size());
        System.out.println(runDaemon.get(0));
//checkboxes
        //input[type='checkbox']:nth-of-type(1)
/*
        //find all elements with tag name input
        List<WebElement> checkboxes=DriverFactory.getDriver().findElements(By.cssSelector("input[type='checkbox']"));
        //"input[type='checkbox']:nth-of-type(1)"
        //loop through this list of elements
        for (WebElement checkbox: checkboxes){
            Thread.sleep(3000);
            //if checkbox is not selected yet
                if (!checkbox.isSelected()) {
                    //select it
                    BrowserUtils.wait(5);
                    checkbox.click();
                    BrowserUtils.wait(5);
                }
            }

 */
        //WebElement firstCheckbox = DriverFactory.getDriver().findElement(By.xpath("(//input[@type='checkbox'])[1]"));
      deliveryConfigModule.firstCheckbox.click();
        BrowserUtils.wait(5);

    }
    @Then("I should be able to check Run Daemon Second box")
    public void iShouldBeAbleToCheckRunDaemonSecondBox() throws InterruptedException {

   //     WebElement secondCheckbox = DriverFactory.getDriver().findElement(By.xpath("(//input[@type='checkbox'])[2]"));
        Thread.sleep(5000);
       deliveryConfigModule. secondCheckbox.click();
        BrowserUtils.wait(10);

        List<String> runDaemon1 = DBCon.readDataBasePDK("Select DISPLAY_NAME,RUN_DAEMON Module From NPDREPORTMANAGER_DQIBLD.TB_FF_MODULE_SUMMARY_SCHEMA WHERE DISPLAY_NAME='Canada Beauty POS Monthly - FF Profile'", "Module");
        System.out.println(runDaemon1);

    }
}


        /*
      if(  firstCheckbox.isSelected()){
          firstCheckbox.click();
        }else{
          BrowserUtils.wait(5);
          firstCheckbox.click();
        }
      BrowserUtils.wait(7);
      if (!secondCheckbox.isSelected()){
          secondCheckbox.click();
      }else {
          BrowserUtils.wait(5);
          secondCheckbox.click();
      }


         */

