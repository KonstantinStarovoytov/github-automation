package utils;

import lombok.Getter;
import lombok.experimental.UtilityClass;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@UtilityClass
public class Auth {
    public static final String USER_SESSION = "user_session";

    @Getter
    private static String accessToken = null;

    public static void setAccessToken() {
        accessToken = getWebDriver()
                .manage()
                .getCookieNamed(USER_SESSION)
                .getValue();
    }
}