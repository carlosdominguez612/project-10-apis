package week_10.q1_compliment;


import kong.unirest.core.Unirest;

public class RandomCompliment {

    // Retrieve objects from an API
    // Print response from API

    public static void main(String[] args) {
        // You don't need to modify the main method.
        String compliment = getRandomCompliment();
        System.out.println(compliment);
    }


    public static String getRandomCompliment() {

        // URL String
        String complimentURL = "https://random-compliment.azurewebsites.net/random";

        // Make a request to the Random Compliment API, using the URL String
        // request to the API and store the response in a Compliment object.
        Compliment compliment = Unirest.get(complimentURL)
                .asObject(Compliment.class)
                .getBody();

        // Extract and return the text - the compliment text.
        return compliment.toString();

    }


}
