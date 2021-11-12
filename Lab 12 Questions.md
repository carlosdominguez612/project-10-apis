## Lab 12: Java APIs

**Dependencies**  
The necessary dependencies have already been added to this project for you, so you will not need to modify pom.xml.

**Code needs to meet the requirements given in the instructions**  
The tests for this lab can't check everything about your code since it uses API calls and external APIs. 
It is possible to pass the tests but not meet the assignment expectations. Follow the instructions given carefully. 
Your code will be human-reviewed. Email me if you would like me to check your work.

**Accounts, access keys?**   
The APIs in this lab are open access. 
None of the APIs used here require accounts or authentication. 

**Error handling**   
In a real program, we would need to add error-handling code that anticipates possible errors when making API requests.
For this lab, we will assume that the user enters valid data, 
that the computer running the code has an internet connection, 
the API is working, and that the API server accepts the request 
and provides a response in the expected format.

Obviously there are several possibly ways that things can go wrong - as you work with APIs in future programs, 
you'll think about how to handle these errors. For this lab, assume that the requests will work. 


### Random Compliment API

Finish the program that displays a random compliment.

The API URL you will use is https://random-compliment.herokuapp.com/random

### GitHub User Information API

Finish this program. A user will enter the name of a GitHub user.  

Your program will connect to the GitHub API and request information about a GitHub user,
Their name
Their login name
Their location
The name, language, and size of each of their repositories. 

The API URLs you will use are in this format.  

Assuming we want information about Guido Van Rossum, the Python language creator, whose GitHub username is gvanrossum

User information
https://api.github.com/users/gvanrossum

List of repositories
https://api.github.com/users/gvanrossum/repos

Replace gvanrossum with the username of the user that you want information about. 

Some other usernames you can try are,

* Linus Torvalds's (created the Linux operating system) GitHub username is torvalds
* Moxie Marlinspike's (computer security researcher) GitHub username is moxie0
* Marcus Persson (created Minecraft) GitHub username is xnotch

  
