import burger.ForgotPasswordPage;
import burger.LoginPage;
import burger.MainPage;
import burger.RegisterPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import helpers.User;
import helpers.UserClient;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static helpers.PagesURLs.*;

public class LoginTest {
    User user;
    UserClient userClient;
    Boolean toDelUser;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
        WebDriver driver = new ChromeDriver();
        setWebDriver(driver);
        user = User.getRandomUser();
        userClient = new UserClient();
        toDelUser = false;
    }

    @After
    public void tearDown() {
        Selenide.closeWebDriver();
        if (toDelUser) userClient.deleteUser(user);
    }

    @Test
    @Description("Вход по кнопке «Войти в аккаунт» на главной перенаправляет на главную страницу")
    public void loginFromMainPageTest() {

        RegisterPage registerPage = open(REGISTER_PAGE, RegisterPage.class);

        registerPage.setNameField(user.getName());
        registerPage.setEmailField(user.getEmail());
        registerPage.setPasswordField(user.getPassword());
        registerPage.clickSignUpButton();

        toDelUser = user.setAccessToken(User.getAccessToken(userClient.loginUser(user)));

        MainPage mainPage = open(MAIN_PAGE, MainPage.class);
        mainPage.pressLogInButton();

        LoginPage loginPage = new LoginPage();
        loginPage.setEmailField(user.getEmail());
        loginPage.setPasswordField(user.getPassword());
        loginPage.clickLogInButton();

        MainPage.collectYouBurgerLabel.shouldBe(Condition.exist);
    }

    @Test
    @Description("Вход через кнопку «Личный кабинет» перенаправляет на главную страницу")
    public void loginFromPersonalAccountTest() {
        RegisterPage registerPage = open(REGISTER_PAGE, RegisterPage.class);

        registerPage.setNameField(user.getName());
        registerPage.setEmailField(user.getEmail());
        registerPage.setPasswordField(user.getPassword());
        registerPage.clickSignUpButton();

        toDelUser = user.setAccessToken(User.getAccessToken(userClient.loginUser(user)));

        MainPage mainPage = open(MAIN_PAGE, MainPage.class);

        mainPage.pressPersonalAccountButton();
        LoginPage loginPage = new LoginPage();
        loginPage.setEmailField(user.getEmail());
        loginPage.setPasswordField(user.getPassword());
        loginPage.clickLogInButton();

        MainPage.collectYouBurgerLabel.shouldBe(Condition.exist);
    }

    @Test
    @Description("Вход через кнопку «Войти» в форме регистрации перенаправляет на главную страницу")
    public void logInFromRegistrationPage() {
        RegisterPage registerPage = open(REGISTER_PAGE, RegisterPage.class);

        registerPage.setNameField(user.getName());
        registerPage.setEmailField(user.getEmail());
        registerPage.setPasswordField(user.getPassword());
        registerPage.clickSignUpButton();

        toDelUser = user.setAccessToken(User.getAccessToken(userClient.loginUser(user)));

        registerPage = open(REGISTER_PAGE, RegisterPage.class);
        registerPage.clickLogInButton();

        LoginPage loginPage = new LoginPage();
        loginPage.setEmailField(user.getEmail());
        loginPage.setPasswordField(user.getPassword());
        loginPage.clickLogInButton();

        MainPage.collectYouBurgerLabel.shouldBe(Condition.exist);
    }

    @Test
    @Description("Вход через кнопку «Войти» в форме восстановления пароля перенаправляет на главную страницу")
    public void logInFromForgotPasswordPage() {
        RegisterPage registerPage = open(REGISTER_PAGE, RegisterPage.class);

        registerPage.setNameField(user.getName());
        registerPage.setEmailField(user.getEmail());
        registerPage.setPasswordField(user.getPassword());
        registerPage.clickSignUpButton();

        toDelUser = user.setAccessToken(User.getAccessToken(userClient.loginUser(user)));

        ForgotPasswordPage forgotPasswordPage = open(FORGOT_PASSWORD_PAGE, ForgotPasswordPage.class);
        forgotPasswordPage.clickLogInButton();

        LoginPage loginPage = new LoginPage();
        loginPage.setEmailField(user.getEmail());
        loginPage.setPasswordField(user.getPassword());
        loginPage.clickLogInButton();

        MainPage.collectYouBurgerLabel.shouldBe(Condition.exist);
    }
}
