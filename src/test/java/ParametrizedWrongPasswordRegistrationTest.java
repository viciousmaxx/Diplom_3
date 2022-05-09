import burger.RegisterPage;
import com.codeborne.selenide.Selenide;
import helpers.User;
import helpers.UserClient;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static helpers.PagesURLs.REGISTER_PAGE;

@RunWith(Parameterized.class)
public class ParametrizedWrongPasswordRegistrationTest {
    private final String pass;
    private final boolean expected;

    User user;
    UserClient userClient;
    ValidatableResponse response;

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

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
        WebDriver driver = new ChromeDriver();
        setWebDriver(driver);
        user = User.getRandomUser();
        userClient = new UserClient();
    }

    @After
    public void tearDown() {
        Selenide.closeWebDriver();
        user.setPassword(pass);
        response = userClient.loginUser(user);
        if (response.extract().body().path("success").equals(true)) {
            user.setAccessToken(User.getAccessToken(userClient.loginUser(user)));
            userClient.deleteUser(user);
        }
    }

    @Test
    @Description("Попытка регистрации с коротким паролем выводит сообщение 'Некорректный пароль'")
    public void registrationWithShortPasswordTest() {
        RegisterPage registerPage = open(REGISTER_PAGE, RegisterPage.class);

        registerPage.setNameField(user.getName());
        registerPage.setEmailField(user.getEmail());
        registerPage.setPasswordField(pass);
        registerPage.clickSignUpButton();

        Boolean actual = registerPage.errorLabel.exists();
        Assert.assertEquals(expected, actual);
    }
}
