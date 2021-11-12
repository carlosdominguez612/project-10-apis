package week_12.q1_compliment;

import kong.unirest.ObjectMapper;
import kong.unirest.Unirest;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


public class RandomComplimentTest {

    private final int TIMEOUT = 20000;  // 20 seconds, big timeout since API will be slow to wake up if not used for an hour.

    private static ObjectMapper originalMapper;

    @BeforeClass
    public static void saveOriginalMapper(){
        originalMapper = Unirest.config().getObjectMapper();
    }

    @After
    public void resetMapper() {
        Unirest.config().setObjectMapper(originalMapper);
    }


    @Test(timeout = TIMEOUT)
    public void getRandomComplimentUsesCorrectAPIURL() {

        // Mock Unirest's .get method and ensure it uses the correct URL.
        // Ensure it's called with the correct API string
        try( MockedStatic<Unirest> mockUnirest = mockStatic(Unirest.class) ) {

            String expectedURL = "https://random-compliment.herokuapp.com/random";

            mockUnirest.when( () -> Unirest.get(expectedURL)).thenReturn(null);

            try {
                String whatever = RandomCompliment.getRandomCompliment();
            } catch (NullPointerException e) {
                // expecting a null pointer here because the mock methods return null when called
            }

            mockUnirest.verify(() -> Unirest.get(expectedURL));

        } catch (Exception e) {
            System.err.println(e);
            // Unirest get not used in the expected way, or some other error.
            fail("Use Unirest to get the random compliment.");
        }
    }



    private Class findComplimentResponseClass() {

        try {
            Class complimentClass = Class.forName("week_12.q1_compliment.Compliment");
            System.out.println("Found Compliment class, standalone class as expected.");
            return complimentClass;
        } catch(Exception e) {

            // not found, check for nested class but tell student to move to standalone
            try {
                // nested classes not allowed. Can't create as easily with reflection.
                Class complimentResponse = Class.forName("week_12.q1_compliment.RandomCompliment$Compliment");
                System.err.println("Found Compliment class, defined as a nested class. " +
                        "\nMove the class definition outside of the RandomCompliment class.");
                return null;
            } catch(Exception ex) {
                System.out.println("Did not find Compliment class as a nested class either");
            }
        }
        return null;
    }


    @Test(timeout = TIMEOUT)
    public void testGetRandomCompliment() throws Exception {

        System.out.println("This test makes an API call. " +
                "\nSince the API is slow to respond when it hasn't been used for an hour, this test may take 10 seconds to complete.");

        String exampleCompliment = "You are an outstandingly great Java programmer!";

        Class complimentResponseClass = findComplimentResponseClass();
        assertNotNull("Create a Compliment class", complimentResponseClass);

        Constructor[] constructors = complimentResponseClass.getDeclaredConstructors();

        Object randomComplimentObject = null;
        try {
            Constructor c = constructors[0];
            System.out.println(constructors);
            System.out.println(c + " " + Arrays.toString(c.getParameterTypes()));
            randomComplimentObject = constructors[0].newInstance(null);

            // Use getDeclaredConstructors to get the default constructor java makes if the class does not
            // have a constructor.
            Constructor defaultNoArgConstructor = complimentResponseClass.getDeclaredConstructor();

            // TODO this does not work when the class is nested. instructions specify standalone class
            randomComplimentObject = defaultNoArgConstructor.newInstance();

        } catch (Exception e) {
            System.err.println(e);
            fail("Can't create a mock response object because " + e.getMessage());
        }

        // either one text field or setName field
        Field[] fields = complimentResponseClass.getFields();
        System.out.println(Arrays.toString(fields));
        if (fields.length == 1) {
            Field textField = fields[0];
            // check the type, name in another test
            textField.set(randomComplimentObject, exampleCompliment);
        }

        else {
            // there must be setText method
            Method setText = complimentResponseClass.getMethod("setText", String.class);
            setText.invoke(randomComplimentObject, exampleCompliment);
        }

        // So we should have an object with the pre-packaged string defined above,
        // regardless of what the API actually returns

        // Replace default object mapper with one that always returns the mock randomComplimentObject
        // object with mock data
        Unirest.config().setObjectMapper(new MockObjectMapper(randomComplimentObject));

        String compliment = RandomCompliment.getRandomCompliment();

        assertEquals("Return the text from the API response.", exampleCompliment, compliment);
    }


    class MockObjectMapper implements ObjectMapper {

        private Object ob;
        MockObjectMapper(Object ob){
            this.ob = ob;
        }
        @Override
        public <T> T readValue(String s, Class<T> aClass) {
            // convert JSON string to object
            return (T) ob;
        }

        @Override
        public String writeValue(Object o) {
            return null;  // writes object to JSON string
        }
    }

    @Test(timeout = TIMEOUT)
    public void ComplimentResponseClassCreated() {

        // Ensure correct Compliment class is created
        // It can work as a nested class of RandomCompliment or a standalone but lab
        // requires standalone class.  Standalone classes are usually recommended anyway.

        // Ensure it has one field, a String text
        // or get and set text methods
        String msg = "Can't find Compliment class. Use this exact name for the class. " +
                "\nDefine this class in the same file as RandomCompliment (but not as a nested class) or a new file in the q1_compliment package (directory).";

        Class complimentResponseClass = findComplimentResponseClass();
        assertNotNull(msg, complimentResponseClass);

        Field[] fields = complimentResponseClass.getFields();

        // Student may use a public field,
        if (fields.length == 1) {
            String name = fields[0].getName();
            assertEquals("Compliment class should have one String field called 'text'", name, "text");

            Class type = fields[0].getType();
            assertEquals("Compliment class should have one String field called 'text'", String.class, type);
        }

        // Or, a private field of any name, and public or protected get and set methods. Private fields not returned by getFields
        else if (fields.length == 0) {
            try {
                Method getText = complimentResponseClass.getMethod("getText");
                Method setText = complimentResponseClass.getMethod("setText", String.class);
                assertEquals("If you use getter and setter methods, the return type from getText should be String.", String.class, getText.getReturnType());
            } catch (Exception e) {
                System.err.println(e);
                fail("Create a public text field; or a private text field with getter and setter methods");
            }
        }

        else {
            // Extra fields?  Not needed, so test fail
            fail("The Compliment class should only have the field(s) and/or methods needed to map to the JSON response.\n" +
                    "Don't add any other fields or methods");
        }
    }


    @Test(timeout = TIMEOUT)
    public void humanReview() {

        fail("This test will always fail.\n" +
                "The tests for this lab can't check everything about your code since it uses API calls and external APIs. \n" +
                "It is possible to write code that passes the tests, but does not meet the assignment expectations. \n" +
                "Carefully follow the instructions given. \n" +
                "Your code will be human-reviewed. Please email me if you would like me to check your work.");
    }
}