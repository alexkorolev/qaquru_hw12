import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class Main extends BaseTest{

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final String issueNumer = "#80";

    @Test
    void testGithub(){
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");
        $(".header-search-button").click();
        $("#query-builder-test").sendKeys(REPOSITORY);
        $("#query-builder-test").submit();

        $(linkText(REPOSITORY)).click();
        $("#issues-tab").click();
        $(withText(issueNumer)).should(Condition.exist);
    }

    @Test
    void testWithLambda(){
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });
        step("Открываем строку поиска", () -> {
            $(".header-search-button").click();
        });
        step("Ввести текст в строку поиска", () -> {
            $("#query-builder-test").sendKeys(REPOSITORY);
        });
        step("Ввести текст в строку поиска", () -> {
            $("#query-builder-test").submit();
        });
        step("Открыть проект по назанию " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Открыть таб с проблемами", () -> {
            $("#issues-tab").click();
        });
        step("Проверить число ошибок", () -> {
            $(withText(issueNumer)).should(Condition.exist);
        });
    }

    @Test
    void testWithAnnotation(){
        WebSteps steps = new WebSteps();
        steps.openPage("https://github.com");
        steps.makeSearchbyName(REPOSITORY);
        steps.openProjectByName(REPOSITORY);
        steps.openTab();
        steps.checkIssue(issueNumer);
    }
}
