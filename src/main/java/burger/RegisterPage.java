package burger;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class RegisterPage {

    static SelenideElement nameField = $(By.xpath("//fieldset[1]/div/div/input"));
    static SelenideElement emailField = $(By.xpath("//fieldset[2]/div/div/input"));
    static SelenideElement passwordField = $(By.cssSelector(".text[type='password']"));
    static SelenideElement signUpButton = $(By.xpath(".//button[text()='Зарегистрироваться']"));
    static SelenideElement logInButton = $(By.xpath(".//a[text()='Войти']"));
    public static SelenideElement errorLabel = $(By.xpath(".//fieldset[3]/div/p[text()='Некорректный пароль']"));

    @DisplayName("Ввод значения в поле 'Имя'")
    @Step("Ввод значения в поле 'Имя'")
    public void setNameField(String name) {
        nameField.setValue(name);
    }

    @DisplayName("Ввод значения в поле 'E-mail'")
    @Step("Ввод значения в поле 'E-mail'")
    public void setEmailField(String value) {
        emailField.setValue(value);
    }

    @DisplayName("Ввод значения в поле 'Пароль'")
    @Step("Ввод значения в поле 'Пароль'")
    public void setPasswordField(String value) {
        passwordField.setValue(value);
    }

    @DisplayName("Клик по кнопке 'Зарегистрироваться'")
    @Step("Клик по кнопке 'Зарегистрироваться'")
    public void clickSignUpButton() {
        signUpButton.click();
    }

    @DisplayName("Клик по кнопке 'Войти'")
    @Step("Клик по кнопке 'Войти'")
    public void clickLogInButton() {
        logInButton.click();
    }
}
