package com.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormTest {

    @BeforeAll
    static void setup() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillAllFieldsTest() {
        open("/automation-practice-form");

        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#firstName").setValue("Jane");
        $("#lastName").setValue("Doe");
        $("#userEmail").setValue("jana@test.com");
        $("#userNumber").setValue("1234567890");
        $("#subjectsInput").setValue("Computer Science").pressTab();

        $("#genterWrapper").$(byText("Female")).click();
        $("#hobbiesWrapper").$(byText("Sports")).click();

        $("#uploadPicture").uploadFromClasspath("test-img.jpg");

        $("#currentAddress").setValue("17926 Eleanora Islands");

        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("May");
        $(".react-datepicker__year-select").selectOption("2014");
        $(".react-datepicker__day.react-datepicker__day--004:not(.react-datepicker__day--outside-month)").click();

        $("#submit").submit();

        $(".modal-dialog .modal-title").shouldHave(text("Thanks for submitting the form"));

        $(".table").$(byText("Student Name")).parent().shouldHave(text("Jane"));
        $(".table").$(byText("Student Name")).parent().shouldHave(text("Doe"));
        $(".table").$(byText("Student Email")).parent().shouldHave(text("jana@test.com"));
        $(".table").$(byText("Gender")).parent().shouldHave(text("Female"));
        $(".table").$(byText("Mobile")).parent().shouldHave(text("1234567890"));
        $(".table").$(byText("Date of Birth")).parent().shouldHave(text("04 May,2014"));
        $(".table").$(byText("Subjects")).parent().shouldHave(text("Computer Science"));
        $(".table").$(byText("Hobbies")).parent().shouldHave(text("Sports"));
        $(".table").$(byText("Picture")).parent().shouldHave(text("test-img.jpg"));
        $(".table").$(byText("Address")).parent().shouldHave(text("17926 Eleanora Islands"));
        $(".table").$(byText("State and City")).parent().shouldHave(text("NCR Delhi"));

//      sleep(600_000);
    }
}