package week_10.q1_compliment;

// Java class that maps to the structure of the response.
public class Compliment {
    String text;

    // .toString constructor
    @Override
    public String toString() {
        return text;
    }

    // Getter and Setter
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
