package page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {
    public final SelenideElement
            firstName = $("#firstName"),
            lastName = $("#lastName"),
            userEmail = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            userNumber = $("#userNumber"),
            dateOfBirthInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            hobbiesWrapper = $("#hobbiesWrapper"),
            uploadPicture = $("#uploadPicture"),
            currentAddress = $("#currentAddress"),
            stateInput = $("#react-select-3-input"),
            cityInput = $("#react-select-4-input"),
            submitButton = $("#submit"),
            resultsTable = $(".table-responsive"),
            closeModal = $("#closeLargeModal");

    public PracticeFormPage openPage() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public PracticeFormPage setFirstName(String value) {
        firstName.setValue(value);
        return this;
    }

    public PracticeFormPage setLastName(String value) {
        lastName.setValue(value);
        return this;
    }

    public PracticeFormPage setEmail(String value) {
        userEmail.setValue(value);
        return this;
    }

    public PracticeFormPage selectGender(String gender) {
        genderWrapper.$(byText(gender)).click();
        return this;
    }

    public PracticeFormPage setPhone(String phone) {
        userNumber.setValue(phone);
        return this;
    }

    public PracticeFormPage setBirthDate(String date) {

        dateOfBirthInput.click();
        for (int i = 0; i < 10; i++) {
            dateOfBirthInput.sendKeys(Keys.BACK_SPACE);
        }


        dateOfBirthInput.sendKeys(date);


        dateOfBirthInput.sendKeys(Keys.HOME);
        dateOfBirthInput.sendKeys(Keys.DELETE);
        dateOfBirthInput.sendKeys(Keys.ENTER);

        return this;
    }

    public PracticeFormPage setSubject(String subject) {
        subjectsInput.setValue(subject).pressEnter();
        return this;
    }

    public PracticeFormPage selectHobby(String hobby) {
        hobbiesWrapper.$(byText(hobby)).click();
        return this;
    }

    public PracticeFormPage uploadFile(String fileName) {
        uploadPicture.uploadFromClasspath(fileName);
        return this;
    }

    public PracticeFormPage setAddress(String address) {
        currentAddress.setValue(address);
        return this;
    }

    public PracticeFormPage selectState(String state) {
        stateInput.setValue(state).pressEnter();
        return this;
    }

    public PracticeFormPage selectCity(String city) {
        cityInput.setValue(city).pressEnter();
        return this;
    }

    public PracticeFormPage submitForm() {
        submitButton.click();
        return this;
    }

    public PracticeFormPage verifyResult(String key, String value) {
        resultsTable.$(byText(key)).parent().shouldHave(text(value));
        return this;
    }

    public void closeModal() {
        closeModal.click();
    }

    public void name() {
    }
}



