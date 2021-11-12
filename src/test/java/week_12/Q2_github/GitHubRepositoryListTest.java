package week_12.Q2_github;

import org.junit.BeforeClass;
import org.junit.Test;
import week_12.test_utils.PrintUtils;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class GitHubRepositoryListTest {

    Class userClass;
    Field loginField;
    Field locationField;
    Field nameField;

    Class repoClass;
    Field languageField;
    Field repoNameField;

    private final int TIMEOUT = 20000;  // 20 seconds, big timeout since API will be slow to wake up if not used for an hour.


    @BeforeClass
    public static void gitHubRepositoryList() throws Exception {

        Class userClass = GitHubUser.class;
        Field loginField = userClass.getField("login");
        Field locationField = userClass.getField("location");
        Field nameField = userClass.getField("name");

        Class repoClass = GitHubRepository.class;
        Field languageField = repoClass.getField("language");
        Field repoNameField = repoClass.getField("name");

    }


    @Test(timeout = TIMEOUT)
    public void getUserLocation() throws Exception {

        GitHubUser user = GitHubUserInformation.getUserInformation("claraj");

        String loginName = (String) loginField.get(user);
        assertEquals("claraj", loginName);

        String location = (String) loginField.get(user);
        assertEquals("Minneapolis, MN", location);

        String name = (String) nameField.get(user);
        assertEquals("Clara", name);


        user = GitHubUserInformation.getUserInformation("hello-java-class");

        loginName = (String) loginField.get(user);
        assertEquals("hello-java-class", loginName);

        location = (String) loginField.get(user);
        assertEquals("Canada", location);

        name = (String) nameField.get(user);
        assertEquals("Hello! Java! Class!!", name);

    }


    @Test(timeout = TIMEOUT)
    public void getRepositories() throws Exception {

        GitHubUser user = new GitHubUser();
        loginField.set(user, "hello-java-class");

        GitHubRepository[] repositories = GitHubUserInformation.getRepositories(user);

        assertEquals(2, repositories.length);

        // Expect repositories in alphabetical order, so owls then spells.

        GitHubRepository owlRepo = repositories[0];
        String repoName = (String) repoNameField.get(owlRepo);
        assertEquals("owls", repoName);
        String repoLang = (String) languageField.get(owlRepo);
        assertEquals("Python", repoLang);

        GitHubRepository spellsRepo = repositories[1];
        repoName = (String) repoNameField.get(owlRepo);
        assertEquals("spells", repoName);
        repoLang = (String) languageField.get(owlRepo);
        assertEquals("C#", repoLang);

    }


    @Test(timeout = TIMEOUT)
    public void displayGitHubUserInformation() throws Exception {

        GitHubUser exampleUser = new GitHubUser();

        loginField.set(exampleUser, "Hermione Granger");
        locationField.set(exampleUser, "London, England");

        // And some example repositories, need a name and language

        GitHubRepository repo1 = new GitHubRepository();
        languageField.set(repo1, "JavaScript");
        nameField.set(repo1, "Spells");

        GitHubRepository repo2 = new GitHubRepository();
        languageField.set(repo2, "C#");
        nameField.set(repo2, "Owls");

        GitHubRepository[] repos = { repo1, repo2 };

        PrintUtils.catchStandardOut();
        GitHubUserInformation.displayGitHubUserInformation(exampleUser, repos);
        String textPrinted = PrintUtils.resetStandardOut();

        assertTrue("Print the user's name", textPrinted.contains("Hermione Granger"));
        assertTrue("Print the user's location", textPrinted.contains("London, England"));
        assertTrue("Print the name of each repository", textPrinted.contains("Spells"));
        assertTrue("Print the name of each repository", textPrinted.contains("Owls"));

    }


    @Test(timeout = TIMEOUT)
    public void humanReview() {

        fail("This test will always fail.\n" +
                "The tests for this lab can't check everything about your code since it uses API calls and external APIs. \n" +
                "It is possible to pass the tests but not meet the assignment expectations. \n" +
                "Carefully follow the instructions given. \n" +
                "Your code will be human-reviewed. Please email me if you would like me to check your work.");
    }

}