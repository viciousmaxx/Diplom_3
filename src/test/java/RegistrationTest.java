import burger.LoginPage;
import burger.MainPage;
import burger.RegisterPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import helpers.User;
import helpers.UserClient;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static helpers.PagesURLs.LOGIN_PAGE;
import static helpers.PagesURLs.REGISTER_PAGE;

public class RegistrationTest {
    User user;
    UserClient userClient;
    ValidatableResponse response;

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
        response = userClient.loginUser(user);
        if (response.extract().body().path("success").equals(true)) {
            user.setAccessToken(User.getAccessToken(userClient.loginUser(user)));
            userClient.deleteUser(user);
        }
    }

    @Test
    @Description("Успешная регистрация перенаправляет на страницу авторизации")
    public void successfulRegistrationTest() {
        RegisterPage registerPage = open(REGISTER_PAGE, RegisterPage.class);

        registerPage.setNameField(user.getName());
        registerPage.setEmailField(user.getEmail());
        registerPage.setPasswordField(user.getPassword());
        registerPage.clickSignUpButton();

        webdriver().shouldHave(url(LOGIN_PAGE));

        //добавил шаг попытки авторизации, чтобы уйти от неявного ожидания.
        LoginPage loginPage = new LoginPage();
        loginPage.setEmailField(user.getEmail());
        loginPage.setPasswordField(user.getPassword());
        loginPage.clickLogInButton();
        MainPage mainPage = new MainPage();
        mainPage.collectYouBurgerLabel.shouldBe(Condition.exist);
    }
}
