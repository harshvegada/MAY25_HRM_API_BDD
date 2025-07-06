package utility;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class GenerateData {

    static Faker faker;

    static {
        faker = new Faker();
    }

    public static String getFirstName() {
        return faker.name().firstName();
    }

    public static String getWebSite() {
        return "https://" + faker.company().url();
    }

    public static String getLastName() {
        return faker.name().lastName();
    }

    public static String getMiddleName() {
        return faker.name().firstName();
    }

    public static int getRandomNumber() {
        return faker.number().randomDigit();
    }

    public static boolean getRandomBooleanFlag() {
        return faker.bool().bool();
    }

    public static String getPastDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(faker.date().past(365, TimeUnit.DAYS));
    }

    public static String getFuture() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(faker.date().future(365, TimeUnit.DAYS));
    }

    public static String getRandomString() {
        return faker.lorem().fixedString(50);
    }

    public static String getUserName() {
        return faker.name().username();
    }

    public static String getCompanyName() {
        return faker.company().name();
    }

    public static String getAddress() {
        return faker.address().fullAddress();
    }

    public static String getPassword() {
        return faker.crypto().sha1();
    }

    public static String getPhoneNumber() {
        return "8905741289";
    }

    public static String getProgrammingLanguageName() {
        return faker.programmingLanguage().name();
    }

    public static String getSomeDescription() {
        return faker.lorem().sentence();
    }
}
