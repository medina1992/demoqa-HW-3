package components;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    private final SelenideElement dateOfBirthInput = $("#dateOfBirthInput");

    public void setDate(String date) {
        dateOfBirthInput.click();

        // Очистка поля через BACK_SPACE
        for (int i = 0; i < 10; i++) {
            dateOfBirthInput.sendKeys(Keys.BACK_SPACE);
        }

        dateOfBirthInput.sendKeys(date);
        dateOfBirthInput.sendKeys(Keys.HOME, Keys.DELETE, Keys.ENTER);
    }
}




