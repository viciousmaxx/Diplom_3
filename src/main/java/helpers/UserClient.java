package helpers;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

public class UserClient extends RestClient{
    @DisplayName("Удаление пользователя")
    @Step("Удаление пользователя")
    public void deleteUser(User user) {
        RestAssured.given()
                .spec(getBaseSpec())
                .auth().oauth2(user.getAccessToken())
                .body(user)
                .when()
                .delete("api/auth/user/")
                .then()
                .log().all();
    }

    @DisplayName("Логин пользователя")
    @Step("Логин пользователя")
    public ValidatableResponse loginUser(User user) {
        return RestAssured.given()
                .spec(getBaseSpec())
                .body(user)
                .when()
                .post("api/auth/login/")
                .then()
                .log().ifError();
    }
}
