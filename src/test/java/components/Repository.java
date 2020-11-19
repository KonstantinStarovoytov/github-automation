package components;

import com.codeborne.selenide.SelenideElement;
import core.annotations.PageComponent;
import core.annotations.RootLocator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@PageComponent
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RootLocator("[itemprop='owns']")
@Getter
public class Repository extends MultipleBaseComponent{

    SelenideElement title = self().$("a");
    SelenideElement description = self().$("p");
    SelenideElement programmingLanguageName = self().$("[itemprop='programmingLanguage']");
    SelenideElement programmingLanguageColorDot = self().$(".repo-language-color");

    public Repository(SelenideElement self) {
        super(self);
    }
}