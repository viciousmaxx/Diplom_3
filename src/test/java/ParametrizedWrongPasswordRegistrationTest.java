import burger.RegisterPage;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static helpers.PagesURLs.REGISTER_PAGE;
import static com.codeborne.selenide.Selenide.open;

@RunWith(Parameterized.class)
public class ParametrizedWrongPasswordRegistrationTest {
    private final String pass;
    private final boolean expected;

    public ParametrizedWrongPasswordRegistrationTest(String pass, boolean expected) {
        this.pass = pass;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "Проверяемый пароль: {0}, отображение ошибки: {1}")
    public static Object[][] checkName() {
        return new Object[][]{
                //Минимальный пароль — шесть символов
                {RandomStringUtils.randomAlphabetic(0), false},
                {RandomStringUtils.randomAlphabetic(1), true},
                {RandomStringUtils.randomAlphabetic(5), true},
                {RandomStringUtils.randomAlphabetic(6), false}};
    }

    @Test
    @Description("Попытка регистрации с коротким паролем выводит сообщение 'Некорректный пароль'")
    public void registrationWithShortPasswordTest() {
        RegisterPage registerPage = open(REGISTER_PAGE, RegisterPage.class);

        registerPage.setNameField(Faker.instance().name().name());
        registerPage.setEmailField(Faker.instance().internet().emailAddress());
        registerPage.setPasswordField(pass);
        registerPage.clickSignUpButton();

        Boolean actual = RegisterPage.errorLabel.exists();
        Assert.assertEquals(expected, actual);
        Selenide.closeWebDriver();
    }
}
