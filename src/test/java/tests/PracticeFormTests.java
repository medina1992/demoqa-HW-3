package tests;

import org.junit.jupiter.api.Test;
import page.PracticeFormPage;

public class PracticeFormTests extends BaseTest {

    PracticeFormPage formPage = new PracticeFormPage();
    // позитивный тестовый сценарий E2E

    @Test
    void fillFormTest() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String phone = faker.phoneNumber().subscriberNumber(10);
        String address = faker.address().fullAddress();
        String gender = "Female";
        String birthDate = "09 Jul 1992";
        String subject = "Maths";
        String hobby = "Music";
        String picture = "foto.jpg";
        String state = "NCR";
        String city = "Delhi";
        formPage.removeBanners()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .selectGender("Female")
                .setPhone(phone)
                .setBirthDate("09 Jul 1992")
                .setSubject("Maths")
                .selectHobby("Music")
                .uploadFile("foto.jpg")
                .setAddress(address)
                .selectState("NCR")
                .selectCity("Delhi")
                .submitForm()
                .verifyResult("Student Name", firstName + " " + lastName)
                .verifyResult("Student Email", email)
                .verifyResult("Gender", gender)
                .verifyResult("Mobile", phone)
                .verifyResult("Date of Birth", "09 July,1992")
                .verifyResult("Subjects", "Maths")
                .verifyResult("Hobbies", "Music")
                .verifyResult("Picture", "foto.jpg")
                .verifyResult("Address", address)
                .verifyResult("State and City", "NCR Delhi");

        formPage.closeModal();
    }

    //негативный тестовый сценарий (не заполняем обязательные поля)
    @Test
    void negativeFormTest_MissingRequiredFields() {
        String lastName = faker.name().lastName();
        String Email = faker.internet().emailAddress();

        formPage.removeBanners()
                .setLastName(lastName)
                .setEmail(Email)
                .selectGender("Female")
                .submitForm();

        formPage.assertModalIsNotVisible();
    }

    //позитивная проверка по минилаьным количествам вводимых обязательных параметров
    @Test
    void formShouldBeSubmittedWithMinimalRequiredFields() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String phone = faker.phoneNumber().phoneNumber();

        formPage.removeBanners()
                .setFirstName("Medina")
                .setLastName("Akhundova")
                .selectGender("Female")
                .setPhone("9967962177")
                .submitForm()
                .verifyResult("Student Name", firstName + " " + lastName)
                .verifyResult("Gender", "Female")
                .verifyResult("Mobile", phone);

        formPage.closeModal();
    }
}
