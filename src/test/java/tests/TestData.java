package tests;

import com.github.javafaker.Faker;
import data.BirthDate;

public class TestData {
    private static final Faker faker = new Faker();

    public final String firstName = faker.name().firstName();
    public final String lastName = faker.name().lastName();
    public final String email = faker.internet().emailAddress();
    public final String phone = faker.phoneNumber().subscriberNumber(10);
    public final String address = faker.address().fullAddress();
    public final String gender = "Female";

    public final String day = String.format("%02d", faker.number().numberBetween(1, 28));
    public final String month = faker.options().option(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    );
    public final String year = String.valueOf(faker.number().numberBetween(1990, 2023));
    public final BirthDate birthDate = new BirthDate(day, month, year);

    public final String subject = faker.options().option("Maths", "Physics", "Chemistry", "Biology", "English");
    public final String hobby = faker.options().option("Music", "Reading", "Sports");
    public final String picture = faker.options().option("foto.jpg");

    public final String state = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    public final String city = switch (state) {
        case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
        case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");
        case "Haryana" -> faker.options().option("Karnal", "Panipat");
        case "Rajasthan" -> faker.options().option("Jaipur", "Jaiselmer");
        default -> "Delhi";
    };
    public final String stateAndCity = state + " " + city;
}
