package tests;

import com.codeborne.selenide.junit5.ScreenShooterExtension;
import core.annotations.WithoutLogin;
import core.spring.SpringConfig;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Cookie;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pages.LoginPage;

import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.FileDownloadMode.FOLDER;
import static com.codeborne.selenide.SelectorMode.Sizzle;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.util.Optional.ofNullable;
import static org.openqa.selenium.UnexpectedAlertBehaviour.ACCEPT;
import static org.openqa.selenium.remote.CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR;
import static utils.Auth.*;

@Slf4j
@ExtendWith({SpringExtension.class, ScreenShooterExtension.class})
@ContextConfiguration(classes = SpringConfig.class)
public abstract class BaseTest {

    @BeforeAll
    public static void setupGlobalConfiguration() {
        baseUrl = "https://github.com/";
        timeout = 6000;
        pollingInterval = 200;
        pageLoadTimeout = 15000;
        browser = CHROME;
        startMaximized = true;
        selectorMode = Sizzle;
        savePageSource = false;
        fileDownload = FOLDER;
        downloadsFolder = "target/downloads/";
        browserCapabilities.setCapability(UNEXPECTED_ALERT_BEHAVIOUR, ACCEPT);
    }

    @SneakyThrows
    @BeforeEach
    public void driverInitialization(TestInfo testInfo) {
        open(baseUrl);
        getWebDriver().manage().addCookie(new Cookie("_device_id", "be383b8ef24b09a980805ef04830b61d" ));
        if (!testInfo.getTestMethod().orElseThrow().isAnnotationPresent(WithoutLogin.class)) {
            doLogin();
        }
    }

    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }

    private void doLogin() {
        ofNullable(getAccessToken()).ifPresentOrElse(token -> {
            getWebDriver().manage().addCookie(new Cookie(USER_SESSION, token));
            open(baseUrl);
        }, () -> {
            new LoginPage().login();
            setAccessToken();
        });
    }
}