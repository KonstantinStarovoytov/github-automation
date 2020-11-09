package pages;

import components.Header;
import components.Repository;
import core.annotations.Lazywired;
import core.annotations.PageObject;
import lombok.Getter;

import java.util.List;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static constants.Constants.BACKGROUND_COLOR;
import static core.ComponentUtils.listOf;
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
        repositoryBlocks.get(0).getTitle().shouldHave(text(repositories.get(0).getName()));
        repositoryBlocks.get(0).getProgrammingLanguageName().shouldHave(text(repositories.get(0).getLanguage().getName()));
        repositoryBlocks.get(0).getProgrammingLanguageColorDot().shouldHave(
                cssValue(BACKGROUND_COLOR, repositories.get(0).getLanguage().getColor().getRgba()));
        repositoryBlocks.get(1).getTitle().shouldHave(text(repositories.get(1).getName()));
        repositoryBlocks.get(2).getTitle().shouldHave(text(repositories.get(2).getName()));
        repositoryBlocks.get(2).getDescription().shouldHave(text(repositories.get(2).getDescription()));
        repositoryBlocks.get(2).getProgrammingLanguageName().shouldHave(text(repositories.get(2).getLanguage().getName()));
        repositoryBlocks.get(2).getProgrammingLanguageColorDot().shouldHave(
                cssValue(BACKGROUND_COLOR, repositories.get(2).getLanguage().getColor().getRgba()));
        repositoryBlocks.get(3).getTitle().shouldHave(text(repositories.get(3).getName()));
        repositoryBlocks.get(3).getDescription().shouldHave(text(repositories.get(3).getDescription()));
        repositoryBlocks.get(3).getProgrammingLanguageName().shouldHave(text(repositories.get(3).getLanguage().getName()));
        repositoryBlocks.get(3).getProgrammingLanguageColorDot().shouldHave(
                cssValue(BACKGROUND_COLOR, repositories.get(3).getLanguage().getColor().getRgba()));
    }
}