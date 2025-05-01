package tests;

import components.CalendarComponent;
import data.BirthDate;
import org.junit.jupiter.api.Test;
import page.PracticeFormPage;
import org.junit.jupiter.api.BeforeEach;
import com.github.javafaker.Faker;



public class PracticeFormTests extends BaseTest {
    private Faker faker;
    PracticeFormPage formPage = new PracticeFormPage();

    @BeforeEach
    public void setUp() {
        faker = new Faker();
    }

    public PracticeFormTests setBirthDate(String day, String month, String year) {
        calendar.setDate(day, month, year);
        return this;
    }


    private final CalendarComponent calendar = new CalendarComponent();

    TestData data = new TestData();
    // Позитивный тестовый сценарий E2E
    @Test
    void fillFormTest() {

        BirthDate birthDate = new BirthDate("09", "July", "1992"); // Создание объекта TestData

        formPage.removeBanners()
                .setFirstName(data.firstName)  // Используем данные из TestData
                .setLastName(data.lastName)
                .setEmail(data.email)
                .selectGender(data.gender)  // Нет необходимости в toString(), так как это уже строка
                .setPhone(data.phone)
                .setBirthDate(birthDate)  // Доступ к данным о дате
                .setSubject(data.subject)  // Нет необходимости в toString()
                .selectHobby(data.hobby)  // Нет необходимости в toString()
                .uploadFile(data.picture)
                .setAddress(data.address)
                .selectState(data.state)  // Нет необходимости в toString()
                .selectCity(data.city)  // Нет необходимости в toString()
                .submitForm()
                .verifyResult("Student Name", data.firstName + " " + data.lastName)
                .verifyResult("Student Email", data.email)
                .verifyResult("Gender", data.gender)  // Проверка по строковому значению
                .verifyResult("Mobile", data.phone)
                .verifyResult("Date of Birth", data.birthDate.getFormattedForCheck())  // Форматированная дата
                .verifyResult("Subjects", data.subject)  // Проверка по строковому значению
                .verifyResult("Hobbies", data.hobby)  // Проверка по строковому значению
                .verifyResult("Picture", data.picture)
                .verifyResult("Address", data.address)
                .verifyResult("State and City", data.state + " " + data.city);

        formPage.closeModal();
    }

    // Негативный тестовый сценарий (не заполняем обязательные поля)
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

    // Позитивная проверка минимальных обязательных параметров
    @Test

    void formShouldBeSubmittedWithMinimalRequiredFields() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String phone = faker.phoneNumber().phoneNumber();

        formPage.removeBanners()
                .setFirstName(firstName)
                .setLastName(lastName)
                .selectGender(data.gender)
                .setPhone(phone)
                .submitForm()
                .verifyResult("Student Name", data.firstName + " " + data.lastName)
                .verifyResult("Gender", data.gender)
                .verifyResult("Mobile", data.phone);

        formPage.closeModal();
    }
}
