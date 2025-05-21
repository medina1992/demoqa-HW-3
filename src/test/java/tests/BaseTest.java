package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class BaseTest {

    @BeforeAll
    static void setup() throws Exception {
        Configuration.remote = "http://selenoid.autotests.cloud:8080/wd/hub";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";

        // Создаем уникальную временную папку для user-data-dir
        Path userDataDir = Files.createTempDirectory("chrome-user-data-");

        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--disable-dev-shm-usage",
                "--no-sandbox",
                "--user-data-dir=" + userDataDir.toAbsolutePath().toString()
        );

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        // Включаем видео и VNC в Selenoid
        capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableVideo", true
        ));

        Configuration.browserCapabilities = capabilities;
    }
}
