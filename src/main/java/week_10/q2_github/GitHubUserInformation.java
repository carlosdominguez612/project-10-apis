package week_10.q2_github;


import kong.unirest.core.Unirest;

import static input.InputUtils.stringInput;

public class GitHubUserInformation {

    public static void main(String[] args) {

        // You don't need to modify this method.
        String username = stringInput("Enter a GitHub user's name: ");
        GitHubUser user = getUserInformation(username);
        GitHubRepository[] repositories = getRepositories(user);
        displayGitHubUserInformation(user, repositories);

    }

    public static GitHubUser getUserInformation(String gitHubUserName) {

        /*
        *  The URL to request information about a user is in the form
        *   https://api.github.com/users/{{username}}
        * Where {{username}} should be replaced with the actual username of the GitHub user.
        *
        * For example, for a GitHub user gvanrossum
        * The URL will be https://api.github.com/users/gvanrossum
        *
        * For example, for a GitHub user moxie0
        * The URL will be https://api.github.com/users/moxie0
        */

        // Concat API URL with user input
        String requestURL = "https://api.github.com/users/" + gitHubUserName;

        // Convert the response to a GitHubUser object.
        // This object will store data from the API response.
        GitHubUser user = Unirest.get(requestURL)
                .asObject(GitHubUser.class)
                .getBody();
        // Return the GitHubUser object.
        return user;

    }

    public static GitHubRepository[] getRepositories(GitHubUser user) {

        /*
         *  The URL to request information about a user's repositories is in the form
         *   https://api.github.com/users/{{login}}/repos
         *
         * Where {{login}} is replaced with the actual login username of the GitHub user.
         *
         * For example, for a GitHub user with login gvanrossum
         * The URL will be https://api.github.com/users/gvanrossum/repos
         *
         * For example, for a GitHub user with login moxie0
         * The URL will be https://api.github.com/users/moxie0/repos
         *
         * You can try this URL with your own GitHub login username.  If you have just created your
         * GitHub account for this class, you may not have any public repositories yet - that's ok,
         * you'll see an empty list. The labs for this class are not public repositories.
         * Otherwise, you'll see a list of your public repositories.
         *
         * Notice that the response is a LIST of repository objects.
         * Each repository object has several properties. For this program,
         * we are interested in the repository name, the language, and the size.
         */

        // https://api.github.com/users/{{login}}/repos

        // Concat API URL with login + /repos, to reach repos
        String requestURLwLogin = "https://api.github.com/users/" + user.login +  "/repos";

        // Convert the response to an array of GitHubRepository objects.
        // These object will store data about each repository from the API response.
        GitHubRepository[] repositories = Unirest.get(requestURLwLogin)
                .asObject(GitHubRepository[].class)
                .getBody();

        // Return the GitHubRepository array.
        return repositories;
    }


    public static void displayGitHubUserInformation(GitHubUser user, GitHubRepository[] repositories) {

        // display information about the GitHub user.
        //  Neatly display (print)
        //      The GitHub user's login,
        //      Their name
        //      Their location

        // for each repository,
        //  Neatly display
        //      the repository's name
        //      the repository's language
        //      the repository's size. Include the units, KB, with the number.
        //          So, if a repository's size is 100, display "100 KB" with a space between the number and "KB".

        System.out.println(user.toString() + "\n"
        + "GitHub Repositories: ");
        for (GitHubRepository repository : repositories) {
            System.out.println(repository.toString());
        }

    }
}