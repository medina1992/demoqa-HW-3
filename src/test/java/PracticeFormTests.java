import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.SPACE;

public class PracticeFormTests {

    @BeforeAll
    static void configureBrowser(){
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";


    }
    @Test
    void fillFormTests() {

        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        $("#firstName").setValue("Medina");
        $("#lastName").setValue("Akhundova");
        $("#userEmail").setValue("medina@gmail.com");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("9967962177");
        $("#dateOfBirthInput").click();
        $("[id=dateOfBirthInput]").sendKeys(Keys.CONTROL + "A");
        $("#dateOfBirthInput").sendKeys(SPACE);
        $("#dateOfBirthInput").setValue("09 Jul 1992").pressEnter();
        $("#subjectsInput").setValue("Maths").pressEnter();
        $("#hobbiesWrapper").$(byText("Music")).click();
        $("#uploadPicture").uploadFromClasspath("foto.jpg");
        $("#currentAddress").setValue("My address");
        $("#react-select-3-input").setValue("NCR").pressEnter();
        $("#react-select-4-input").setValue("Delhi").pressEnter();

        $("#submit"). click();




//Response result

        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text("Medina Akhundova"));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text("medina@gmail.com"));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text("Female"));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text("9967962177"));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text("09 July,1992"));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text("Maths"));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text("Music"));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text("foto.jpg"));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text("My address"));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text("NCR Delhi"));

        $("#closeLargeModal"). click();


            }

    @AfterEach
    public void closeDriver() {
           closeWebDriver();
    }



}

