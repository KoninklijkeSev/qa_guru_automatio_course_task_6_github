package tests;

import org.junit.jupiter.api.Test;
import com.github.javafaker.Faker;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestsStudentRegistrationForm extends BaseClass {
    Faker faker = new Faker();
    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            userEmail = faker.internet().emailAddress("email"),
            userNumber = faker.phoneNumber().subscriberNumber(10),
            month = "January",
            year = "2021",
            day = "1",
            subject = "Maths",
            picture = "Picture.jpg",
            address = faker.address().streetAddress(),
            state = "NCR",
            city = "Delhi",
            genderMale = "Male",
            hobbiesSports = "Sports";

    @Test
    void studentRegistrationForm() {
        open("https://demoqa.com/automation-practice-form");
        $(byText("Practice Form")).shouldHave(text("Practice Form"));
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        // gender-radio-1
        $(byText(genderMale)).click();
        $("#userNumber").setValue(userNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__month").$(byText(day)).click();
        $("#subjectsInput").setValue(subject).pressEnter();
        // hobbies-checkbox-1
        $(byText(hobbiesSports)).click();
        // uploadPicture
        $("#uploadPicture").uploadFromClasspath(picture);
        //$("#uploadPicture").pressEnter();
        $("#currentAddress").setValue(address);
        // Select State
        $("#state").click();
        $(byText(state)).click();
        // city
        $("#city").click();
        $(byText(city)).click();
        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $("tr:nth-of-type(1) > td:nth-of-type(2)").shouldHave(text(firstName + " " + lastName));
        $("tr:nth-of-type(2) > td:nth-of-type(2)").shouldHave(text(userEmail));
        $("tr:nth-of-type(3) > td:nth-of-type(2)").shouldHave(text(genderMale));
        $("tr:nth-of-type(4) > td:nth-of-type(2)").shouldHave(text(userNumber));
        $("tr:nth-of-type(5) > td:nth-of-type(2)").shouldHave(text(day + " " + month + "," + year));
        $("tr:nth-of-type(6) > td:nth-of-type(2)").shouldHave(text(subject));
        $("tr:nth-of-type(7) > td:nth-of-type(2)").shouldHave(text(hobbiesSports));
        $("tr:nth-of-type(8) > td:nth-of-type(2)").shouldHave(text(picture));
        $("tr:nth-of-type(9) > td:nth-of-type(2)").shouldHave(text(address));
        $("tr:nth-of-type(10) > td:nth-of-type(2)").shouldHave(text(state + " " + city));
        $("#closeLargeModal").click();
    }
}
