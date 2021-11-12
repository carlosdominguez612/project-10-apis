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
        * TODO complete the GitHubUser class
        *  GitHubUser should have public fields for
        *  The login
        *  The user's location
        *  The user's name
        *
        * Ensure the names and types of these fields match the data returned in an API response.
        *
        * TODO make a request to the API
        *  Convert the response to a GitHubUser object.
        *  This object will store data from the API response.
        *  Return the GitHubUser object.
        *
        * */



        return null;   // todo delete and replace with your own code

    }

    public static GitHubRepository[] getRepositories(GitHubUser user) {

        /*
         *  The URL to request information about a user's repositories is in the form
         *   https://api.github.com/users/{{username}}/repos
         * Where {{username}} is replaced with the actual username of the GitHub user.
         *
         * For example, for a GitHub user gvanrossum
         * The URL will be https://api.github.com/users/gvanrossum/repos
         *
         * For example, for a GitHub user moxie0
         * The URL will be https://api.github.com/users/moxie0/repos
         *
         * You can try this URL with your own GitHub username.
         *
         * Notice that the response is a LIST of repository objects.
         * Each repository object has several properties. For this program,
         * we are interested in the name and the language.
         *
         * TODO use the gitHubUser parameter to create a string URL
         *  that can be used to request information about this user.
         *
         * TODO complete the GitHubRepository class
         *  GitHubRepository should have public fields for
         *  The repository name
         *  The repository language
         *
         * Ensure the names and types of these fields match the data returned in an API response.
         *
         * TODO make a request to the API
         *
         *  Convert the response to an array of GitHubRepository objects.
         *  These object will store data about each repository from the API response.
         *  Return the GitHubRepository array.
         *
         * */

        return null;  // TODO delete and replace with your code
    }


    public static void displayGitHubUserInformation(GitHubUser user, GitHubRepository[] repositories) {

        // TODO display information about the GitHub user.
        //  Display (print) the GitHub user's login,
        //   Their name
        //   Their location

        // TODO for each repository,
        //  display the repository's name
        //  the repository's language

    }
}
