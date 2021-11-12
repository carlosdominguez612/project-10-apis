package week_12.Q1_compliment;

public class RandomCompliment {


    public static void main(String[] args) {
        // You don't need to modify the main method
        String compliment = getRandomCompliment();
        System.out.println(compliment);
    }


    public static String getRandomCompliment() {

        /*
        * TODO use Unirest to make a request to the Random Compliment API, using the URL provided below.
        *
        * Tip: Paste the URL into your browser address bar to visualize the structure
        * of the response.  You'll see a different compliment every time. An example
        * response looks like this,
        *
        *    {
        *        "text": "You are so creative!"
        *    }
        *
        * TODO Create a Java class that maps to the structure of the response.
         *  The Java class should be called Compliment.
         *  The Compliment class should NOT be a nested class.
        *    Define this class outside of the RandomCompliment class in this file, or in a separate file.
        *  The Compliment class should have a public field, or public get and set methods to save the text property.
        *
        * Remember that field(s) in classes used to store API responses,
        * need to to match the names of properties in the JSON response.
        *
        * TODO Make a request to the API and store the response in a Compliment object.
        *
        * TODO Extract and return the text - the compliment text.
        *
        * */


        // TODO use this URL in your request
        String randomComplimentApiUrl = "https://random-compliment.herokuapp.com/random";

        return null; // TODO delete and replace with your code

    }


}
