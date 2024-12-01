package com.github;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class WikiPageTest {

    @BeforeAll
    static void setup() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com/";
    }

    @Test
    void goToPageTest() {
        String pageName = "SoftAssertions";
        ElementsCollection wikiPagesLinks = $$("#wiki-pages-box ul > li summary a");

        open("selenide/selenide");

        $("#wiki-tab").click();
        $("#wiki-pages-filter").setValue(pageName);
        wikiPagesLinks.shouldHave(itemWithText(pageName));
        wikiPagesLinks.findBy(text(pageName)).click();

        $("#wiki-body").shouldHave(text("@ExtendWith({SoftAssertsExtension.class})\n" +
                "class Tests {\n" +
                "  @Test\n" +
                "  void test() {\n" +
                "    Configuration.assertionMode = SOFT;\n" +
                "    open(\"page.html\");\n" +
                "\n" +
                "    $(\"#first\").should(visible).click();\n" +
                "    $(\"#second\").should(visible).click();\n" +
                "  }\n" +
                "}"));
    }
}
