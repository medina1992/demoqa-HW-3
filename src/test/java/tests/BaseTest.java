package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import helpers.AllureAttachments;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {

    @BeforeAll
    static void setup() {
        Configuration.remote = "http://selenoid.autotests.cloud:8080/wd/hub";
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAllureAttachments() {
        try {
            if (WebDriverRunner.hasWebDriverStarted()) {
                AllureAttachments.screenshotAs("Last screenshot");
                AllureAttachments.pageSource();
                AllureAttachments.browserConsoleLogs();

                try {
                    RemoteWebDriver driver = (RemoteWebDriver) WebDriverRunner.getWebDriver();
                    String sessionId = driver.getSessionId().toString();
                    AllureAttachments.attachVideo(sessionId);
                } catch (Exception e) {
                    System.out.println("Video attachment skipped: " + e.getMessage());
                }

                closeWebDriver();
            } else {
                System.out.println("WebDriver not started. Skipping attachments.");
            }
        } catch (Exception e) {
            System.out.println("Error during attachments: " + e.getMessage());
        }
    }
}
