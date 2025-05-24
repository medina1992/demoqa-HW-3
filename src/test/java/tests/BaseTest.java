package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class BaseTest {


    @BeforeAll
    static void setup() throws IOException {
        Configuration.remote = System.getProperty("remoteUrl", "http://selenoid.autotests.cloud:4444/wd/hub");
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browserVersion", "127.0");
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";

        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--disable-dev-shm-usage",
                "--no-sandbox"
        );

        Path userDataDir = Files.createTempDirectory("chrome-user-data");
        options.addArguments("--user-data-dir=" + userDataDir.toAbsolutePath());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableVideo", true
        ));

        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    public void addAttachments() {
        // Скриншот
        byte[] screenshot = ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment("Last screenshot", new ByteArrayInputStream(screenshot));

        // Page source
        String pageSource = WebDriverRunner.source();
        Allure.addAttachment("Page source", "text/html", pageSource, ".html");

        // Browser logs
        LogEntries logs = WebDriverRunner.getWebDriver().manage().logs().get(LogType.BROWSER);
        StringBuilder logBuilder = new StringBuilder();
        for (LogEntry logEntry : logs) {
            logBuilder.append(logEntry.getLevel()).append(" ").append(logEntry.getMessage()).append("\n");
        }
        Allure.addAttachment("Browser console logs", logBuilder.toString());

        // Видео
        try {
            String sessionId = ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString();
            String videoUrl = "https://selenoid.autotests.cloud/video/" + sessionId + ".mp4";

            Allure.addAttachment("Video", "text/html",
                    new ByteArrayInputStream((
                            "<html><body><video width='100%' height='100%' controls autoplay>" +
                                    "<source src='" + videoUrl + "' type='video/mp4'>" +
                                    "</video></body></html>").getBytes()), ".html");

        } catch (Exception e) {
            System.out.println("Видео не прикреплено: " + e.getMessage());
        }

        Selenide.closeWebDriver();
    }
}


