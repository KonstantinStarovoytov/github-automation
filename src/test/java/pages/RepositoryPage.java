package pages;

import components.Header;
import components.Repository;
import core.ParametrizedSelenideElement;
import core.annotations.Lazywired;
import core.annotations.PageObject;
import lombok.Getter;
import lombok.experimental.ExtensionMethod;

import java.util.List;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static constants.Constants.BACKGROUND_COLOR;
import static core.ComponentUtils.listOf;
import static java.util.Optional.ofNullable;
import static java.util.stream.IntStream.iterate;
import static utils.ResourceReader.getDefaultUser;

@PageObject
public class RepositoryPage extends BasePage {

    @Lazywired
    @Getter
    private Header header;

    public List<Repository> getRepositories() {
        return listOf(Repository.class);
    }

    public void checkRepositories() {
        final var repositories = getDefaultUser().getRepositories();
        final var repositoryBlocks = getRepositories();
        iterate(0, i -> i + 1).limit(repositories.size()).forEach(counter -> {
            final var repositoryDto = repositories.get(counter);
            repositoryBlocks.get(counter).getTitle().shouldHave(text(repositoryDto.getName()));
            ofNullable(repositoryDto.getDescription())
                    .ifPresent(description -> repositoryBlocks.get(counter).getDescription().shouldHave(text(description)));
            ofNullable(repositoryDto.getLanguage()).ifPresent(language -> {
                repositoryBlocks.get(counter).getProgrammingLanguageName().shouldHave(text(language.getName()));
                repositoryBlocks.get(counter).getProgrammingLanguageColorDot()
                        .shouldHave(cssValue(BACKGROUND_COLOR, language.getColor().getRgba()));
            });
        });
    }
}