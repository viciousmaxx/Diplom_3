package burger;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class UserAccountPage {

    public SelenideElement youCanEditCredentialsLabel = $(By.xpath("//p[text()='В этом разделе вы можете изменить свои персональные данные']"));

    SelenideElement exitButton = $(By.xpath("//button[text()='Выход']"));

    public void pressExitButton() {
        exitButton.click();
    }
}
