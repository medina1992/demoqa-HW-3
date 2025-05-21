package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import helpers.AllureAttachments;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {

    @BeforeAll
    static void setup() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";

        // Если используешь Selenoid
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-notifications");
        options.addArguments("--window-size=1920,1080");


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    public void addAttachments() {
        AllureAttachments.screenshotAs("Last screenshot");
        AllureAttachments.pageSource();
        AllureAttachments.browserConsoleLogs();

        // Если сессия — удалённая, добавляем видео
        try {
            RemoteWebDriver driver = (RemoteWebDriver) WebDriverRunner.getWebDriver();
            String sessionId = driver.getSessionId().toString();
            AllureAttachments.attachVideo(sessionId);
        } catch (Exception e) {
            System.out.println("Video attachment skipped: " + e.getMessage());
        }
        try {
            Thread.sleep(1000); // Пауза перед закрытием браузера
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        closeWebDriver(); // закрываем браузер
    }
}
