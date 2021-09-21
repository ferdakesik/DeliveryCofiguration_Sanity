package deliveryConfigurationSanity.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    protected DriverFactory(){}
    protected static WebDriver driver;

    public static WebDriver getDriver() {
        if(driver == null){
            String browser = ConfigurationReader.getProperty("browser");

            //for specifying browser type using command line (i.e mvn clean test -Dbrowser="chrome" )
            if(System.getProperty("browser")!=null) browser=System.getProperty("browser");

            synchronized (DriverFactory.class) {
                switch (browser.toLowerCase()) {
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        driver = new ChromeDriver();
                        break;
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        driver = new FirefoxDriver();
                        break;
                    case "edge":
                        WebDriverManager.edgedriver().setup();
                        driver = new EdgeDriver();
                        break;
                    case "ie":
                        WebDriverManager.iedriver().setup();
                        driver = new InternetExplorerDriver();
                        break;
                    case "safari":
                        WebDriverManager.getInstance(SafariDriver.class).setup();
                        driver = new SafariDriver();
                        break;

                    case "headless-chrome": //For running chrome driver without interface
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions chromeOptions1 = new ChromeOptions();
                        chromeOptions1.setHeadless(true);
                        driver = new ChromeDriver(chromeOptions1);
                        break;
                    case "headless-firefox": //headless mode
                        WebDriverManager.firefoxdriver().setup();
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        firefoxOptions.setHeadless(true);
                        driver = new FirefoxDriver(firefoxOptions);
                        break;
                    case "remote-chrome":
                        try {
                            URL gridUrl = new URL("http://localhost:4444/wb/hub"); // if your grid is not local, change the address and maybe port as well.
                            ChromeOptions chromeOptions2 = new ChromeOptions();
                            chromeOptions2.setCapability("platform", Platform.ANY);
                            driver = new RemoteWebDriver(gridUrl, chromeOptions2);
                        } catch (MalformedURLException e) {
                            System.out.println("you may need to change URL or ChromeOptions     " + e.getMessage());
                            e.printStackTrace();
                        }
                    case "remote-firefox":
                        try {
                            URL gridUrl = new URL("http://localhost:4444/wb/hub"); // check your url and port
                            FirefoxOptions firefoxOptions2 = new FirefoxOptions();
                            firefoxOptions2.setCapability("platform", Platform.ANY);
                            driver = new RemoteWebDriver(gridUrl, firefoxOptions2);
                        } catch (MalformedURLException e) {
                            System.out.println("you may need to change URL or FirefoxOptions     " + e.getMessage());
                            e.printStackTrace();
                        }
                    case "remote-safari":
                        try {
                            URL gridUrl = new URL("http://localhost:4444/wb/hub"); // if your grid is not local, change the address and maybe port as well.
                            SafariOptions safariOptions = new SafariOptions();
                            safariOptions.setCapability("platform", Platform.MAC);
                            driver = new RemoteWebDriver(gridUrl, safariOptions);
                        } catch (MalformedURLException e) {
                            System.out.println("Check URL or SafariOptions     " + e.getMessage());
                            e.printStackTrace();
                        }

                    default:
                        throw new WebDriverException("No such a driver!");
                }
            }
        }
        return driver;
    }

    public static void quit(){
        if(driver!=null){
            driver.quit();
            driver=null;
        }
    }

    public static void closeDriver(){
        if (driver!=null){
            driver.close();

        }
    }





}
