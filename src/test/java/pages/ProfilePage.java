package pages;

import components.RepositoryProfile;
import core.annotations.PageObject;

import java.util.List;

import static core.ComponentUtils.listOf;

@PageObject
public class ProfilePage extends BasePage{

    public List<RepositoryProfile> getRepositories() {
        return listOf(RepositoryProfile.class);
    }
}