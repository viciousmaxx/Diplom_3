import burger.MainPage;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import org.junit.Test;

import static helpers.PagesURLs.MAIN_PAGE;
import static com.codeborne.selenide.Selenide.open;

public class MainPageTest {

    @Test
    @Description("Раздел «Конструктор»: работают переходы к разделу: «Булки»")
    public void pathBunsIsAvailableTest() {
        MainPage mainPage = open(MAIN_PAGE, MainPage.class);
        mainPage.pressSauceHeaderButton();
        mainPage.pressBunsHeaderButton();
        MainPage.bunsSubHeaderLabel.shouldBe(Condition.visible);
    }

    @Test
    @Description("Раздел «Конструктор»: работают переходы к разделу: «Соусы»")
    public void pathSauceIsAvailableTest() {
        MainPage mainPage = open(MAIN_PAGE, MainPage.class);
        mainPage.pressSauceHeaderButton();
        MainPage.saucesSubHeaderLabel.shouldBe(Condition.visible);
    }

    @Test
    @Description("Раздел «Конструктор»: работают переходы к разделу: «Начинки»")
    public void pathFillersIsAvailableTest() {
        MainPage mainPage = open(MAIN_PAGE, MainPage.class);
        mainPage.pressFillersHeaderButton();
        MainPage.fillersSubHeaderLabel.shouldBe(Condition.visible);
    }
}
