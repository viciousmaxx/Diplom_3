import burger.RegisterPage;
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
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.codeborne.selenide.WebDriverRunner.driver;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static helpers.PagesURLs.LOGIN_PAGE;
import static helpers.PagesURLs.REGISTER_PAGE;

public class RegistrationTest {
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
    @Description("Успешная регистрация перенаправляет на главную страницу")
    public void successfulRegistrationTest() {
        RegisterPage registerPage = open(REGISTER_PAGE, RegisterPage.class);

        registerPage.setNameField(user.getName());
        registerPage.setEmailField(user.getEmail());
        registerPage.setPasswordField(user.getPassword());
        registerPage.clickSignUpButton();

        webdriver().shouldHave(url(LOGIN_PAGE));

        //секунда на запись в БД свежесозданного юзера (иначе падает операция получения токена для удаления пользователя)
        driver().actions().pause(1000);
        toDelUser = user.setAccessToken(User.getAccessToken(userClient.loginUser(user)));
    }
}
