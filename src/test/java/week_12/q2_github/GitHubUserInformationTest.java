package week_12.q2_github;

import org.junit.BeforeClass;
import org.junit.Test;
import week_12.test_utils.PrintUtils;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class GitHubUserInformationTest {

    static Class userClass;
    static Field loginField;
    static Field locationField;
    static Field userNameField;

    static Class repoClass;
    static Field languageField;
    static Field repoNameField;
    static Field repoSizeField;

    private final int TIMEOUT = 20000;  // 20 seconds, big timeout since API will be slow to wake up if not used for an hour.


    @BeforeClass
    public static void gitHubRepositoryList() throws Exception {

         userClass = GitHubUser.class;
         loginField = userClass.getField("login");
         locationField = userClass.getField("location");
         userNameField = userClass.getField("name");

         repoClass = GitHubRepository.class;
         languageField = repoClass.getField("language");
         repoNameField = repoClass.getField("name");
         repoSizeField = repoClass.getField("size");
    }


    @Test(timeout = TIMEOUT)
    public void getUserLocation() throws Exception {

        GitHubUser user = GitHubUserInformation.getUserInformation("claraj");

        String loginName = (String) loginField.get(user);
        assertEquals("claraj", loginName);

        String location = (String) locationField.get(user);
        assertEquals("Minneapolis, MN", location);

        String name = (String) userNameField.get(user);
        assertEquals("Clara", name);


        user = GitHubUserInformation.getUserInformation("hello-java-class");

        loginName = (String) loginField.get(user);
        assertEquals("hello-java-class", loginName);

        location = (String) locationField.get(user);
        assertEquals("Canada", location);

        name = (String) userNameField.get(user);
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
        int repoSize = repoSizeField.getInt(owlRepo);
        assertEquals(1, repoSize);

        GitHubRepository spellsRepo = repositories[1];
        repoName = (String) repoNameField.get(spellsRepo);
        assertEquals("spells", repoName);
        repoLang = (String) languageField.get(spellsRepo);
        assertEquals("C#", repoLang);
        repoSize = repoSizeField.getInt(spellsRepo);
        assertEquals(16, repoSize);

    }


    @Test(timeout = TIMEOUT)
    public void displayGitHubUserInformation() throws Exception {

        GitHubUser exampleUser = new GitHubUser();

        loginField.set(exampleUser, "Hermione Granger");
        locationField.set(exampleUser, "London, England");

        // And some example repositories, need a name and language and size

        GitHubRepository repo1 = new GitHubRepository();
        languageField.set(repo1, "JavaScript");
        repoNameField.set(repo1, "Spells");
        repoSizeField.set(repo1, 404);

        GitHubRepository repo2 = new GitHubRepository();
        languageField.set(repo2, "C#");
        repoNameField.set(repo2, "Owls");
        repoSizeField.set(repo2, 12345678);

        GitHubRepository[] repos = { repo1, repo2 };

        PrintUtils.catchStandardOut();
        GitHubUserInformation.displayGitHubUserInformation(exampleUser, repos);
        String textPrinted = PrintUtils.resetStandardOut();

        assertTrue("Print the user's name", textPrinted.contains("Hermione Granger"));
        assertTrue("Print the user's location", textPrinted.contains("London, England"));
        assertTrue("Print the size of each repository. Include the units, KB", textPrinted.contains("404 KB"));

        assertTrue("Print the name of each repository", textPrinted.contains("Spells"));
        assertTrue("Print the name of each repository", textPrinted.contains("Owls"));
        assertTrue("Print the size of each repository. Include the units, KB", textPrinted.contains("12345678 KB"));

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