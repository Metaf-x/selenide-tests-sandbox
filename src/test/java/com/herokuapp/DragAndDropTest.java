package com.herokuapp;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.DragAndDropOptions.to;
import static com.codeborne.selenide.Selenide.*;

public class DragAndDropTest {

    SelenideElement columnA = $("#column-a");
    SelenideElement columnB = $("#column-b");

    @BeforeAll
    static void setup() {
        Configuration.browserSize = "1920x1080";
    }
    @Test
    void actionsDragAndDropTest() {
        open("https://the-internet.herokuapp.com/drag_and_drop");

        columnA.shouldHave(text("A"));
        actions().moveToElement(columnA).clickAndHold().moveByOffset(200, 0).release().perform();
        columnA.shouldHave(text("B"));
    }

    @Test
    void dragAndDropTest() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        columnA.shouldHave(text("A"));
        columnA.dragAndDrop(to(columnB));
        columnA.shouldHave(text("B"));
    }
}
