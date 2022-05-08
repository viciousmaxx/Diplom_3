import burger.LoginPage;
import burger.MainPage;
import burger.RegisterPage;
import burger.UserAccountPage;
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

import static burger.LoginPage.*;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static helpers.PagesURLs.*;

public class PersonalAccountTest {
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
    @Description("Переход в личный кабинет без авторизации перенаправляет на страницу авторизации")
    public void personalAccountNotAvailableWithoutAuthorizationTest() {
        MainPage mainPage = open(MAIN_PAGE, MainPage.class);
        mainPage.pressPersonalAccountButton();
        logInLabel.shouldBe(Condition.exist);
    }

    @Test
    @Description("Переход в личный кабинет авторизованного пользователя перенаправляет на страницу профиля")
    public void personalAccountAvailableWithAuthorizationTest() {
        RegisterPage registerPage = open(REGISTER_PAGE, RegisterPage.class);
        MainPage mainPage = new MainPage();
        LoginPage loginPage = new LoginPage();
        UserAccountPage userAccountPage = new UserAccountPage();

        registerPage.setNameField(user.getName());
        registerPage.setEmailField(user.getEmail());
        registerPage.setPasswordField(user.getPassword());
        registerPage.clickSignUpButton();

        toDelUser = user.setAccessToken(User.getAccessToken(userClient.loginUser(user)));

        registerPage = open(REGISTER_PAGE, RegisterPage.class);
        registerPage.clickLogInButton();
        loginPage.setEmailField(user.getEmail());
        loginPage.setPasswordField(user.getPassword());
        loginPage.clickLogInButton();

        mainPage.pressPersonalAccountButton();
        webdriver().shouldHave(url(USER_ACCOUNT_PAGE));
        UserAccountPage.youCanEditCredentialsLabel.shouldBe(Condition.visible);
    }

    @Test
    @Description("Переход из личного кабинета по клику на «Конструктор» перенаправляет на главную страницу")
    public void goToConstructorPageFromPersonalAccountPageIsAvailableTest() {
        RegisterPage registerPage = open(REGISTER_PAGE, RegisterPage.class);
        MainPage mainPage = new MainPage();
        LoginPage loginPage = new LoginPage();

        registerPage.setNameField(user.getName());
        registerPage.setEmailField(user.getEmail());
        registerPage.setPasswordField(user.getPassword());
        registerPage.clickSignUpButton();

        toDelUser = user.setAccessToken(User.getAccessToken(userClient.loginUser(user)));

        registerPage = open(REGISTER_PAGE, RegisterPage.class);
        registerPage.clickLogInButton();
        loginPage.setEmailField(user.getEmail());
        loginPage.setPasswordField(user.getPassword());
        loginPage.clickLogInButton();
        mainPage.pressPersonalAccountButton();

        mainPage.pressConstructorLink();
        MainPage.constructYouBurgerText.shouldBe(Condition.visible);
    }

    @Test
    @Description("Переход из личного кабинета по клику на логотип Stellar Burgers перенаправляет на главную страницу")
    public void goToConstructorPageFromPersonalAccountPageIsAvailableWithHeaderLogoTest() {
        RegisterPage registerPage = open(REGISTER_PAGE, RegisterPage.class);
        MainPage mainPage = new MainPage();
        LoginPage loginPage = new LoginPage();

        registerPage.setNameField(user.getName());
        registerPage.setEmailField(user.getEmail());
        registerPage.setPasswordField(user.getPassword());
        registerPage.clickSignUpButton();

        toDelUser = user.setAccessToken(User.getAccessToken(userClient.loginUser(user)));

        registerPage = open(REGISTER_PAGE, RegisterPage.class);
        registerPage.clickLogInButton();
        loginPage.setEmailField(user.getEmail());
        loginPage.setPasswordField(user.getPassword());
        loginPage.clickLogInButton();
        mainPage.pressPersonalAccountButton();

        mainPage.pressAppHeaderLogo();
        MainPage.constructYouBurgerText.shouldBe(Condition.visible);
    }

    @Test
    @Description("Выход из аккаунта по кнопке «Выйти» в личном кабинете перенаправляет на страницу авторизации")
    public void exitFromPersonalAccountAvailableTest() {
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
        mainPage.pressPersonalAccountButton();
        UserAccountPage userAccountPage = new UserAccountPage();

        userAccountPage.pressExitButton();
        logInLabel.shouldBe(Condition.visible);
    }

}
