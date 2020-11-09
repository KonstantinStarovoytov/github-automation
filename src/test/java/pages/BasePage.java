package pages;

import com.codeborne.selenide.Selenide;
import core.annotations.Url;

import static com.codeborne.selenide.Configuration.baseUrl;
import static java.util.Optional.ofNullable;

public class BasePage {

    public void openPage() {
        ofNullable(this.getClass().getDeclaredAnnotation(Url.class))
                .ifPresentOrElse(annotation-> Selenide.open(baseUrl.concat(annotation.value())),
                        ()->Selenide.open(baseUrl));
    }
}