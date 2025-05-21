package page;

import com.codeborne.selenide.SelenideElement;
import components.CalendarComponent;
import components.ResultTableComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {

    private final CalendarComponent calendar = new CalendarComponent();
    private final ResultTableComponent resultTable = new ResultTableComponent();

    public final SelenideElement
            firstName = $("#firstName"),
            lastName = $("#lastName"),
            userEmail = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            userNumber = $("#userNumber"),
            subjectsInput = $("#subjectsInput"),
            hobbiesWrapper = $("#hobbiesWrapper"),
            uploadPicture = $("#uploadPicture"),
            currentAddress = $("#currentAddress"),
            stateInput = $("#react-select-3-input"),
            cityInput = $("#react-select-4-input"),
            submitButton = $("#submit"),
            closeModal = $("#closeLargeModal"),
            modalContent = $(".modal-content");

    public PracticeFormPage openPage() {
        open("/automation-practice-form");
        return this;
    }

    public PracticeFormPage removeBanners() {
        executeJavaScript("document.getElementById('fixedban')?.remove()");
        executeJavaScript("document.querySelector('footer')?.remove()");
        // Удаляем iframe рекламы, если он есть
        executeJavaScript("let adIframe = document.querySelector('iframe[id^=\"google_ads_iframe_\"]'); if(adIframe) adIframe.remove();");
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

    public PracticeFormPage setBirthDate(String day, String month, String year) {
        $("#dateOfBirthInput").click();
        calendar.setDate(day, month, year);
        return this;
    }

    public PracticeFormPage setSubject(String subject) {
        subjectsInput.setValue(subject).pressEnter();
        return this;
    }

    public PracticeFormPage uploadFile(String fileName) {
        uploadPicture.uploadFromClasspath(fileName);
        return this;
    }

    public PracticeFormPage selectHobby(String hobby) {
        hobbiesWrapper.$(byText(hobby)).click();
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
        submitButton.shouldBe(enabled, visible).click();
        return this;
    }

    public PracticeFormPage verifyResult(String key, String value) {
        resultTable.checkResult(key, value);
        return this;
    }

    public void closeModal() {
        closeModal.click();
    }

    public void assertModalIsNotVisible() {
        modalContent.shouldNotBe(visible);
    }
}
