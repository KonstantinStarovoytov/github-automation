package tests;

import core.annotations.Lazywired;
import org.junit.jupiter.api.Test;
import pages.RepositoryPage;

public class RepositoriesTest extends BaseTest{

    @Lazywired
    private RepositoryPage repositoryPage;

    @Test
    public void myProfileTest() {
        repositoryPage.getHeader().openMyRepositories();
        repositoryPage.checkRepositories();
    }
}