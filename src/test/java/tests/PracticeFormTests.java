package tests;

import com.github.javafaker.Faker;
import components.CalendarComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.PracticeFormPage;

public class PracticeFormTests extends BaseTest {
    private final CalendarComponent calendar = new CalendarComponent();
    private final PracticeFormPage formPage = new PracticeFormPage();
    private final TestData data = new TestData();
    private Faker faker;

    @BeforeEach
    public void setUp() {
        faker = new Faker();
    }

    @Test
    void fillFormTest() {
        formPage.removeBanners()
                .setFirstName(data.firstName)
                .setLastName(data.lastName)
                .setEmail(data.email)
                .selectGender(data.gender)
                .setPhone(data.phone)
                .setBirthDate(data.birthDate)
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
                .verifyResult("Date of Birth", data.birthDate.getFormattedForCheck())
                .verifyResult("Subjects", data.subject)
                .verifyResult("Hobbies", data.hobby)
                .verifyResult("Picture", data.picture)
                .verifyResult("Address", data.address)
                .verifyResult("State and City", data.stateAndCity);

        formPage.closeModal();
    }

    @Test
    void negativeFormTest_MissingRequiredFields() {
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();

        formPage.removeBanners()
                .setLastName(lastName)
                .setEmail(email)
                .selectGender(data.gender)
                .submitForm();

        formPage.assertModalIsNotVisible();
    }

    @Test
    void formShouldBeSubmittedWithMinimalRequiredFields() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String phone = faker.phoneNumber().subscriberNumber(10);

        formPage.removeBanners()
                .setFirstName(firstName)
                .setLastName(lastName)
                .selectGender(data.gender)
                .setPhone(phone)
                .submitForm()
                .verifyResult("Student Name", firstName + " " + lastName)
                .verifyResult("Gender", data.gender)
                .verifyResult("Mobile", phone);

        formPage.closeModal();
    }

}
