package week_12.Q2_github;

import java.util.List;

import static input.InputUtils.stringInput;

public class GitHubUserInformation {

    public static void main(String[] args) {

        String username = stringInput("Enter a GitHub user's name: ");
        GitHubUser user = getUserInformation(username);

    }

    public static GitHubUser getUserInformation(String gitHubUserName) {

        /*
        *  The URL to request information about a user is in the form
        *   https://api.github.com/users/{{username}}
        * Where {{username}} is replaced with the actual username of the GitHub user.
        *
        * For example, for a GitHub user gvanrossum
        * The URL will be https://api.github.com/users/gvanrossum
        *
        * For example, for a GitHub user moxie0
        * The URL will be https://api.github.com/users/moxie0
        *
        * You can try this URL with your own GitHub username.
        *
        * TODO use the gitHubUserName parameter to create a string URL
        *  that can be used to request information about this user.
        *
        * TODO create a class called GitHubUser
        *  GitHubUser should have fields for
        *  The login
        *  The user's location
        *  The user's name

        * TODO make a request to the API
        *
        * */



        return null;   // todo delete and replace with your own code

    }

    public static GitHubRepository[] getRepositories(GitHubUser user) {

        // todo

        return null;
    }


    public static void displayGitHubUserInformation(GitHubUser user, GitHubRepository[] repositories) {

        // todo

        // display user's login,
        // name
        // location

        // for each repository, display the repository's name and the language

    }
}
