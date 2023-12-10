import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {

    @Step("Открыть страницу")
    public void openPage(String pageName){
        open(pageName);
    }

    @Step("Выполнить поиск по названию")
    public void makeSearchbyName(String name){
        $(".header-search-button").click();
        $("#query-builder-test").sendKeys(name);
        $("#query-builder-test").submit();
    }

    @Step("Выполнить поиск по названию")
    public void openProjectByName(String name){
        $(linkText(name)).click();
    }

    @Step("Открыть таб")
    public void openTab(){
        $("#issues-tab").click();
    }

    @Step("Проверка количестав ошибок")
    public void checkIssue(String Issue){
        $(withText(Issue)).should(Condition.exist);
    }
}
