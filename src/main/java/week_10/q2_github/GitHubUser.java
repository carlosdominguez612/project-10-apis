package week_10.q2_github;


public class GitHubUser {

    /*
        This should contain public fields for a
        GitHub user's login, their name, and their location.

        For example, in this response,
        https://api.github.com/users/gvanrossum
        The login is "gvanrossum"
        The name is "Guido van Rossum"
        The location is "San Francisco Bay Area"
    */

    public String login;
    public String name;
    public String location;

    @Override
    public String toString() {
        return "Username: " + login + ' ' +
                ", Full Name: " + name + ' ' +
                ", Location: " + location;
    }
}


