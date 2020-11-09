package pages;

import com.codeborne.selenide.SelenideElement;
import components.Header;
import core.annotations.Lazywired;
import core.annotations.PageObject;
import core.annotations.Url;
import dto.UserDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

import static com.codeborne.selenide.Selenide.$;
import static utils.ResourceReader.getDefaultUser;

@PageObject
@Url("login")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class LoginPage extends BasePage{

    @Lazywired
    @NonFinal @Getter
    Header header;

    SelenideElement usernameInput = $("#login_field");
    SelenideElement passwordInput = $("#password");
    SelenideElement signInButton = $(".btn[value='Sign in']");

    public void login(){
        openPage();
        final UserDto defaultUser = getDefaultUser();
        usernameInput.setValue(defaultUser.getLogin());
        passwordInput.setValue(defaultUser.getPassword());
        signInButton.click();
    }
}