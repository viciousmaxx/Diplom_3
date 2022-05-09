package burger;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public SelenideElement logInLabel = $(By.xpath("//h2[text()='Вход']"));
    SelenideElement logInButton = $(By.xpath(".//div/form/button[text()='Войти']"));
    SelenideElement signUpButton = $(By.xpath(".//a[text()='Зарегистрироваться']"));
    SelenideElement passwordField = $(By.cssSelector(".text[type= 'password']"));
    SelenideElement emailField = $(By.cssSelector("input[name='name']"));

    @DisplayName("Ввод значения в поле 'Пароль'")
    @Step("Ввод значения в поле 'Пароль'")
    public void setPasswordField(String value) {
        passwordField.setValue(value);
    }

    @DisplayName("Ввод значения в поле 'E-mail'")
    @Step("Ввод значения в поле 'E-mail'")
    public void setEmailField(String value) { emailField.setValue(value); }

    @DisplayName("Клик по кнопке 'Войти'")
    @Step("Клик по кнопке 'Войти'")
    public void clickLogInButton() {
        logInButton.click();
    }

    @DisplayName("Клик по кнопке 'Зарегистрироваться'")
    @Step("Клик по кнопке 'Зарегистрироваться'")
    public void clickSignUpButton() {
        signUpButton.click();
    }

}
