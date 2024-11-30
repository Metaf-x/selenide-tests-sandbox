package com.github;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class Menu {
    @BeforeAll
    static void setup() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
    }
    @Test
    void selectMenuItemOnHoverTest() {
        open("/");
        $$(".HeaderMenu-link").findBy(exactText("Solutions")).hover().closest(".HeaderMenu-item")
                .$$(".HeaderMenu-dropdown-link").findBy(exactText("Enterprises")).click();
        $("#hero-section-brand-heading").shouldHave(text("The AI-powered developer platform"));
    }
}
