package deliveryConfigurationSanity.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features="src/test/resources",
        glue = "deliveryConfigurationSanity/step_def",
        plugin = {"pretty",
                "rerun:target/rerun.txt",
                "html:target/cucumber-html-reports.html",
                "json:target/cucumber.json"},
        dryRun = false,
        tags={"@Login or @Logout"}

)


public class CucumberRunner {
}
