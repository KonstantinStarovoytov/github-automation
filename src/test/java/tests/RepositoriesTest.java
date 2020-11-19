package tests;

import core.annotations.Lazywired;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.RepositoryPage;

public class RepositoriesTest extends BaseTest{

    @Lazywired
    private RepositoryPage repositoryPage;

    @Test
    @DisplayName("Check repositories")
    public void myProfileTest() {
        repositoryPage.getHeader().openMyRepositories();
        repositoryPage.checkRepositories();
    }
}