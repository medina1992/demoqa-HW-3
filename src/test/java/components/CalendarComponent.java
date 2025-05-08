package components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;

public class CalendarComponent {

    private final SelenideElement dateOfBirthInput = $("#dateOfBirthInput");


    public void setDate(String date) {
        dateOfBirthInput.click();


        for (int i = 0; i < 10; i++) {
            dateOfBirthInput.sendKeys(Keys.BACK_SPACE);
        }


        dateOfBirthInput.sendKeys(date);
        dateOfBirthInput.sendKeys(Keys.ENTER);


        $("#dateOfBirthInput").pressEscape();
    }


    public void setDate(String day, String month, String year) {
        dateOfBirthInput.click();


        $("select.react-datepicker__month-select").shouldBe(Condition.visible).selectOption(month);
        $("select.react-datepicker__year-select").shouldBe(Condition.visible).selectOption(year);


        String formattedDay = Integer.toString(Integer.parseInt(day));


        $$(".react-datepicker__day:not(.react-datepicker__day--outside-month)")
                .findBy(Condition.text(formattedDay))
                .shouldBe(Condition.visible)
                .click();


        $("#dateOfBirthInput").pressEscape();
    }
}
