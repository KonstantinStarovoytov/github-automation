package tests;

import core.annotations.Lazywired;
import core.annotations.WithoutLogin;
import org.junit.jupiter.api.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Lazywired
    private LoginPage loginPage;

    @Test
    @WithoutLogin
    public void successLogin() {
        loginPage.login();
    }
}