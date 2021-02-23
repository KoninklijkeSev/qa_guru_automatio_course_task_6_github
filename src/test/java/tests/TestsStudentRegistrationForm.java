package tests;

import org.junit.jupiter.api.Test;
import com.github.javafaker.Faker;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestsStudentRegistrationForm extends BaseClass {
    Faker faker = new Faker();
    String url = "https://demoqa.com/automation-practice-form";
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
        open(url);
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
        $x("//td[text()=\"Student Name\"]").sibling(0).shouldHave(text(firstName + " " + lastName));
        $x("//td[text()=\"Student Email\"]").sibling(0).shouldHave(text(userEmail));
        $x("//td[text()=\"Gender\"]").sibling(0).shouldHave(text(genderMale));
        $x("//td[text()=\"Mobile\"]").sibling(0).shouldHave(text(userNumber));
        $x("//td[text()=\"Date of Birth\"]").sibling(0).shouldHave(text(day + " " + month + "," + year));
        $x("//td[text()=\"Subjects\"]").sibling(0).shouldHave(text(subject));
        $x("//td[text()=\"Hobbies\"]").sibling(0).shouldHave(text(hobbiesSports));
        $x("//td[text()=\"Picture\"]").sibling(0).shouldHave(text(picture));
        $x("//td[text()=\"Address\"]").sibling(0).shouldHave(text(address));
        $x("//td[text()=\"State and City\"]").sibling(0).shouldHave(text(state + " " + city));
        $("#closeLargeModal").click();
    }
}
