package components;

import com.codeborne.selenide.SelenideElement;
import core.ParametrizedSelenideElement;
import core.annotations.PageComponent;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import static com.codeborne.selenide.Selenide.$;
import static core.ParametrizedSelenideElement.$p;

@PageComponent
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class Header {

    SelenideElement profileAvatarIcon = $("[aria-label='View profile and more']");
    ParametrizedSelenideElement profileOption = $p("[data-ga-click$='%s']");

    public void openMyProfile() {
        profileAvatarIcon.click();
        profileOption.params("your profile").click();
    }

    public void openMyRepositories() {
        profileAvatarIcon.click();
        profileOption.params("your repositories").click();
    }
}