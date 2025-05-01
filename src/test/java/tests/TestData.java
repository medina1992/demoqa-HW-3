package tests;

import com.github.javafaker.Faker;
import data.BirthDate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TestData {
    private static final Faker faker = new Faker();


    public final String firstName = faker.name().firstName();
    public final String lastName = faker.name().lastName();
    public final String email = faker.internet().emailAddress();

    public final String phone = faker.phoneNumber().phoneNumber();
    public final String address = faker.address().fullAddress();

    public final String gender = "Female";  // gender как строка
    public final BirthDate birthDate;

    {
        Date randomBirthDate = faker.date().birthday();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM, yyyy", Locale.ENGLISH);
        String formattedDate = dateFormat.format(randomBirthDate);


        String[] dateParts = formattedDate.split(" ");
        birthDate = new BirthDate(dateParts[0], dateParts[1], dateParts[2]);
    }

    public final String subject = "Maths";
    public final String hobby = "Music";
    public final String picture = "foto.jpg";
    public final String state = "NCR";
    public final String city = "Delhi";
    public final String stateAndCity = state + " " + city;
}
