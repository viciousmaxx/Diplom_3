import burger.MainPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static helpers.PagesURLs.MAIN_PAGE;

public class MainPageTest {
    @After
    public void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    @Description("Раздел «Конструктор»: работают переходы к разделу: «Булки»")
    public void pathBunsIsAvailableTest() {
        MainPage mainPage = open(MAIN_PAGE, MainPage.class);
        mainPage.pressSauceHeaderButton();
        mainPage.pressBunsHeaderButton();
        mainPage.bunsSubHeaderLabel.shouldBe(Condition.visible);
    }

    @Test
    @Description("Раздел «Конструктор»: работают переходы к разделу: «Соусы»")
    public void pathSauceIsAvailableTest() {
        MainPage mainPage = open(MAIN_PAGE, MainPage.class);
        mainPage.pressSauceHeaderButton();
        mainPage.saucesSubHeaderLabel.shouldBe(Condition.visible);
    }

    @Test
    @Description("Раздел «Конструктор»: работают переходы к разделу: «Начинки»")
    public void pathFillersIsAvailableTest() {
        MainPage mainPage = open(MAIN_PAGE, MainPage.class);
        mainPage.pressFillersHeaderButton();
        mainPage.fillersSubHeaderLabel.shouldBe(Condition.visible);
    }
}
