package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import page.PracticeFormPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class PracticeFormTests {

    @BeforeAll
    static void setup() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    PracticeFormPage formPage = new PracticeFormPage();
    // позитивный тестовый сценарий E2E

    @Test
    void fillFormTest() {
        formPage.removeBanners()
                .setFirstName("Medina")
                .setLastName("Akhundova")
                .setEmail("medina@gmail.com")
                .selectGender("Female")
                .setPhone("9967962177")
                .setBirthDate("09 Jul 1992")
                .setSubject("Maths")
                .selectHobby("Music")
                .uploadFile("foto.jpg")
                .setAddress("My address")
                .selectState("NCR")
                .selectCity("Delhi")
                .submitForm()
                .verifyResult("Student Name", "Medina Akhundova")
                .verifyResult("Student Email", "medina@gmail.com")
                .verifyResult("Gender", "Female")
                .verifyResult("Mobile", "9967962177")
                .verifyResult("Date of Birth", "09 July,1992")
                .verifyResult("Subjects", "Maths")
                .verifyResult("Hobbies", "Music")
                .verifyResult("Picture", "foto.jpg")
                .verifyResult("Address", "My address")
                .verifyResult("State and City", "NCR Delhi");

        formPage.closeModal();
    }

    //негативный тестовый сценарий (не заполняем обязательные поля)
    @Test
    void negativeFormTest_MissingRequiredFields() {
        formPage.removeBanners()
                .setLastName("Akhundova")
                .setEmail("medina@gmail.com")
                .selectGender("Female")
                .submitForm();

        formPage.assertModalIsNotVisible();
    }

    //позитивная проверка по минилаьным количествам вводимых обязательных параметров
    @Test
    void formShouldBeSubmittedWithMinimalRequiredFields() {
        formPage.removeBanners()
                .setFirstName("Medina")
                .setLastName("Akhundova")
                .selectGender("Female")
                .setPhone("9967962177")
                .submitForm()
                .verifyResult("Student Name", "Medina Akhundova")
                .verifyResult("Gender", "Female")
                .verifyResult("Mobile", "9967962177");

        formPage.closeModal();
    }


    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }
}
