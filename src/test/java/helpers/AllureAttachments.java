package helpers;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class AllureAttachments {

    public static void screenshotAs(String name) {
        byte[] screenshot = com.codeborne.selenide.Selenide.screenshot(OutputType.BYTES);
        Allure.addAttachment(name, new ByteArrayInputStream(screenshot));
    }

    public static void pageSource() {
        String source = WebDriverRunner.source();
        Allure.addAttachment("Page source", "text/html", source, ".html");
    }

    public static void browserConsoleLogs() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        StringBuilder logs = new StringBuilder();
        for (LogEntry entry : logEntries) {
            logs.append(entry.getLevel()).append(" ").append(entry.getMessage()).append("\n");
        }
        Allure.addAttachment("Browser console logs", logs.toString());
    }


    // Главное: добавление видео из Selenoid
    public static void attachVideo(String sessionId) {
        String videoUrl = "https://selenoid.autotests.cloud/video/" + sessionId + ".mp4";
        try {
            Allure.addAttachment("Video", "video/mp4", new URL(videoUrl).openStream(), "mp4");
        } catch (IOException e) {
            System.out.println("Failed to attach video: " + e.getMessage());
        }
    }
}
