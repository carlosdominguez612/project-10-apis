package week_10.q2_github;


public class GitHubRepository {

    /*
            GitHub repository name,
            The repository language,
            The repository size.  The size is an int number of KB.

        For example, the first repository in this response, as of November 2021,
        https://api.github.com/users/gvanrossum/repos
        The name is "500lines"
        The language is "Python"
        The size is 565.

    */

    public String name;
    public String language;
    public int size;

    @Override
    public String toString() {
        return  "Name: " + name +
                ", language: " + language +
                ", size: " + size + " KB";
    }
}