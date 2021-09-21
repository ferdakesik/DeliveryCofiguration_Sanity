package deliveryConfigurationSanity.utilities;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.BasePropertySet;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.service.schema.ItemSchema;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.MessageBody;
import microsoft.exchange.webservices.data.search.FindItemsResults;
import microsoft.exchange.webservices.data.search.ItemView;
import org.joda.time.LocalDateTime;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.net.URI;
import java.util.Date;

public class EmailUtils {

    private static WebDriver driver;

    public String getSecurityKey(LocalDateTime timeNow, String userEmailAddress, String userEmailPassword, String userFirstName) throws Exception {
        System.out.println("Email Address: " +userEmailAddress);
        System.out.println("Email Password: " +userEmailPassword);
        System.out.println("User's First Name: " +userFirstName);
        String SecurityKey = null;
        boolean receivedAfter = false;
        String Mail_Subject = null;
        String firstName = null;
        long startTime = System.currentTimeMillis();
        do {
            BrowserUtils.wait(20);
            ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
            service.setUrl(new URI("https://webmail.npd.com/ews/Exchange.asmx"));
            ExchangeCredentials credentials = new WebCredentials(userEmailAddress,userEmailPassword);
            service.setCredentials(credentials);
            ItemView view = new ItemView(8);
            FindItemsResults<Item> findResults = service.findItems(WellKnownFolderName.Inbox, view);
            for (Item item : findResults.getItems()){
                if (System.currentTimeMillis() - startTime >= 300000) {
                    System.out.println("Did not receive Verification code email, waited 5 minutes.");
                    Assert.fail("Did not receive Verification code email, waited 5 minutes.");
                }
                item.load(new PropertySet(BasePropertySet.FirstClassProperties, ItemSchema.MimeContent));
                Date date2 = item.getDateTimeReceived();
                System.out.println("Email Received time: " +date2);
                receivedAfter = date2.after(timeNow.toDate());
                System.out.println("Email Received after time now: " + receivedAfter);
                Mail_Subject = item.getSubject();
                System.out.println("Email Subject: " + Mail_Subject);
                MessageBody Message_Body = item.getBody();
                String Message_Body_String = Message_Body.toString();
                if (Message_Body_String.toLowerCase().contains("dear")) {
                    String[] Message_Body_String_Dear = Message_Body_String.split("Dear");
                    String[] First_Name = Message_Body_String_Dear[1].split(",");
                    firstName = First_Name[0].trim();
                    System.out.println("User's First Name: " + firstName);
                }else {
                    firstName = "Did not receive Security Key yet.";
                }
                if (receivedAfter && Mail_Subject.equalsIgnoreCase("Verification Code - The NPD Group") && firstName.equalsIgnoreCase(userFirstName.trim())) {
                    String[] Message_Body_String_Code = Message_Body_String.split("Verification Code:");
                    String[] Security_Key_Before_Code = Message_Body_String_Code[1].split(">");
                    String[] Security_Key_After_Code = Security_Key_Before_Code[1].split("<");
                    SecurityKey = Security_Key_After_Code[0];
                    break;
                }
            }
        }while (receivedAfter == false && Mail_Subject != "Verification Code - The NPD Group" && firstName != userFirstName.trim());
        System.out.println("Security Key: " + SecurityKey);
        return SecurityKey;
    }
    public boolean needToBeVerified(){
        BrowserUtils.wait(20);
        boolean securityPage = false;
        String currentURL = DriverFactory.getDriver().getCurrentUrl();
        System.out.println("Current URL: "+currentURL);
        if (currentURL.toLowerCase().contains("SelfAsserted".toLowerCase()) || currentURL.toLowerCase().contains("b2clogin.com".toLowerCase())){
            securityPage = true;
        }
        System.out.println("Need to be Verified: "+securityPage);
        return securityPage;
    }
}


