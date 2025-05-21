package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.PracticeFormPage;
import static tests.TestData.faker;

public class PracticeFormTests extends BaseTest {
    private PracticeFormPage formPage;
    private final TestData data = new TestData();

    @BeforeEach
    void setUpTest() {

        formPage = new PracticeFormPage();
    }

    @Test
    void fillFormTest() {
        formPage.openPage()
                .removeBanners()
                .setFirstName(data.firstName)
                .setLastName(data.lastName)
                .setEmail(data.email)
                .selectGender(data.gender)
                .setPhone(data.phone)
                .setBirthDate(data.day, data.month, data.year)
                .setSubject(data.subject)
                .selectHobby(data.hobby)
                .uploadFile(data.picture)
                .setAddress(data.address)
                .selectState(data.state)
                .selectCity(data.city)
                .submitForm()
                .verifyResult("Student Name", data.firstName + " " + data.lastName)
                .verifyResult("Student Email", data.email)
                .verifyResult("Gender", data.gender)
                .verifyResult("Mobile", data.phone)
                .verifyResult("Date of Birth", data.day + " " + data.month + "," + data.year)
                .verifyResult("Subjects", data.subject)
                .verifyResult("Hobbies", data.hobby)
                .verifyResult("Picture", data.picture)
                .verifyResult("Address", data.address)
                .verifyResult("State and City", data.stateAndCity);

        formPage.closeModal();
    }


    @Test
    void negativeFormTest_MissingRequiredFields() {

        formPage.openPage()
                .removeBanners()
                .setLastName(data.lastName)
                .setEmail(data.email)
                .selectGender(data.gender)
                .submitForm();

        formPage.assertModalIsNotVisible();
    }

    @Test
    void formShouldBeSubmittedWithMinimalRequiredFields() {

        formPage.openPage()
                .removeBanners()
                .setFirstName(data.firstName)
                .setLastName(data.lastName)
                .selectGender(data.gender)
                .setPhone(data.phone)
                .submitForm()
                .verifyResult("Student Name", data.firstName + " " + data.lastName)
                .verifyResult("Gender", data.gender)
                .verifyResult("Mobile", data.phone);

        formPage.closeModal();
    }

}