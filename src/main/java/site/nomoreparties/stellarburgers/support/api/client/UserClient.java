package site.nomoreparties.stellarburgers.support.api.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import site.nomoreparties.stellarburgers.support.model.AuthUser;
import site.nomoreparties.stellarburgers.support.model.User;

import static io.restassured.RestAssured.given;

public class UserClient {

    public static final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    public static final String REGISTER_USER = "/api/auth/register";
    public static final String USER = "/api/auth/user";
    public static final String LOGIN_USER = "/api/auth/login";


    @Step("Регистрация пользователя")
    public static void register(User user) {
        given()
                .contentType("application/json")
                .baseUri(BASE_URI)
                .body(user)
                .post(REGISTER_USER)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();
    }

    @Step("Удаление пользователя")
    public static void delete(User user) {
        String accessToken = login(user).as(AuthUser.class).getAccessToken();
        given()
                .headers("Authorization", accessToken)
                .baseUri(BASE_URI)
                .delete(USER)
                .then()
                .statusCode(202);
    }

    @Step("Логин пользователя")
    public static Response login(User user) {
        return given()
                .contentType("application/json")
                .baseUri(BASE_URI)
                .body(user)
                .post(LOGIN_USER)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();
    }
}
