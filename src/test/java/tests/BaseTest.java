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
import java.net.URL;
import java.util.Map;

public class BaseTest {



        @BeforeAll
        static void setup() {
            Configuration.remote = "http://selenoid.autotests.cloud:8080/wd/hub";
            Configuration.baseUrl = "https://demoqa.com";
            Configuration.browserSize = "1920x1080";
            Configuration.pageLoadStrategy = "eager";

            ChromeOptions options = new ChromeOptions();
            options.addArguments(
                    "--disable-dev-shm-usage",
                    "--no-sandbox"
            );

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


            String pageSource = WebDriverRunner.source();
            Allure.addAttachment("Page source", "text/html", pageSource, ".html");

            // Логи браузера
            LogEntries logs = WebDriverRunner.getWebDriver().manage().logs().get(LogType.BROWSER);
            StringBuilder logBuilder = new StringBuilder();
            for (LogEntry logEntry : logs) {
                logBuilder.append(logEntry.getLevel()).append(" ").append(logEntry.getMessage()).append("\n");
            }
            Allure.addAttachment("Browser console logs", logBuilder.toString());

            // Видео из Selenoid
            try {
                String sessionId = ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString();
                String videoUrl = "https://selenoid.autotests.cloud/video/" + sessionId + ".mp4";
                Allure.addAttachment("Video", "video/mp4", new URL(videoUrl).openStream(), "mp4");
            } catch (Exception e) {
                System.out.println("Видео не прикреплено: " + e.getMessage());
            }
            Selenide.closeWebDriver();
        }
    }

