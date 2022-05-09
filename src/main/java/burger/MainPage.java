package burger;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    SelenideElement logInButton = $(By.xpath(".//section[2]/div/button[text()='Войти в аккаунт']"));
    public SelenideElement collectYouBurgerLabel = $(By.xpath(".//section[1]/h1[text()='Соберите бургер']"));
    SelenideElement personalAccountButton = $(By.xpath(".//p[text()='Личный Кабинет']"));
    SelenideElement constructorLink = $(By.xpath("//p[text()='Конструктор']"));
    public SelenideElement constructYouBurgerText = $(By.xpath("//h1[text()='Соберите бургер']"));
    SelenideElement appHeaderLogo = $(By.xpath("//nav/div/a[@href='/']"));
    SelenideElement bunsHeaderButton = $(By.xpath("//main/section[1]/div[1]/div[1]"));
    SelenideElement sauceHeaderButton = $(By.xpath("//main/section[1]/div[1]/div[2]"));
    SelenideElement fillersHeaderButton = $(By.xpath("//main/section[1]/div[1]/div[3]"));
    public SelenideElement bunsSubHeaderLabel = $(By.xpath("//h2[text()='Булки']"));
    public SelenideElement saucesSubHeaderLabel = $(By.xpath("//h2[text()='Соусы']"));
    public SelenideElement fillersSubHeaderLabel = $(By.xpath("//h2[text()='Начинки']"));

    @DisplayName("Клик по кнопке 'Войти'")
    @Step("Клик по кнопке 'Войти'")
    public void pressLogInButton() {
        logInButton.click();
    }

    @DisplayName("Клик по кнопке 'Личный Кабинет'")
    @Step("Клик по кнопке 'Личный Кабинет'")
    public void pressPersonalAccountButton() {
        personalAccountButton.click();
    }

    @DisplayName("Клик по кнопке 'Конструктор'")
    @Step("Клик по кнопке 'Конструктор'")
    public void pressConstructorLink() {
        constructorLink.click();
    }

    @DisplayName("Клик по логотипу Stellar Burgers")
    @Step("Клик по логотипу Stellar Burgers")
    public void pressAppHeaderLogo() {
        appHeaderLogo.click();
    }

    @DisplayName("Клик по заголовку 'Булки'")
    @Step("Клик по кнопке 'Булки'")
    public void pressBunsHeaderButton() {
        bunsHeaderButton.click();
    }

    @DisplayName("Клик по заголовку 'Соусы'")
    @Step("Клик по кнопке 'Соусы'")
    public void pressSauceHeaderButton() {
        sauceHeaderButton.click();
    }

    @DisplayName("Клик по заголовку 'Начинки'")
    @Step("Клик по кнопке 'Начинки'")
    public void pressFillersHeaderButton() {
        fillersHeaderButton.click();
    }
}
