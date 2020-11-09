package components;

import com.codeborne.selenide.SelenideElement;
import core.annotations.PageComponent;
import core.annotations.RootLocator;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@PageComponent
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RootLocator(".Box.public")
public class RepositoryProfile extends MultipleBaseComponent {

    SelenideElement title = self().$("span.repo");
    SelenideElement description = self().$(".pinned-item-desc");
    SelenideElement programmingLanguageName = self().$("[itemprop='programmingLanguage']");
    SelenideElement programmingLanguageColorDot = self().$(".repo-language-color");

    public RepositoryProfile(SelenideElement self) {
        super(self);
    }
}