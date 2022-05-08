package burger;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ForgotPasswordPage {
    static SelenideElement logInButton = $(By.xpath(".//a[text()='Войти']"));
    public void clickLogInButton() {
        logInButton.click();
    }
}
