import static org.junit.Assert.*;
import org.junit.Test;

public class ContactTest {

    @Test
    public void runAllTests() {
        testGettersAndSetters();
        testNullInputOnID();
        testShortInputOnID();
        testLongInputOnID();
        testNullInputOnName();
        testShortInputOnName();
        testLongInputOnName();
        testNullInputOnLastName();
        testShortInputOnLastName();
        testLongInputOnLastName();
        testNullInputOnNum();
        testShortInputOnNum();
        testLongInputOnNum();
        testNullInputOnAddress();
        testShortInputOnAddress();
        testLongInputOnAddress();
    }

    @Test
    public void testGettersAndSetters() {
        Contact contact = new Contact("1","John", "Doe", "1234567890", "123 Main St");

        assertEquals("1", contact.getID());
        assertEquals("John", contact.getFirstName());
        assertEquals("Doe", contact.getLastName());
        assertEquals("1234567890", contact.getNumber());
        assertEquals("123 Main St", contact.getAddress());

        contact.setFirstName("Jane");
        assertEquals("Jane", contact.getFirstName());
    }

    @Test
    public void testNullInputOnID() {
        String ID = null;
        String FN = "Jog";
        String LN = "Jogger";
        String NUM = "123311";
        String ADD = "2020 Ave P";

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Contact c = new Contact(ID, FN, LN, NUM, ADD);
        });
    }

    @Test
    public void testShortInputOnID() {
        String ID = "";
        String FN = "Jog";
        String LN = "Jogger";
        String NUM = "123311";
        String ADD = "2020 Ave P";

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Contact c = new Contact(ID, FN, LN, NUM, ADD);
        });
    }

    @Test
    public void testLongInputOnID() {
        String ID = "ASDASDASDASDASDASDSDSDASD";
        String FN = "Jog";
        String LN = "Jogger";
        String NUM = "123311";
        String ADD = "2020 Ave P";

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Contact c = new Contact(ID, FN, LN, NUM, ADD);
        });
    }

    @Test
    public void testNullInputOnName() {
        String ID = "1";
        String FN = null;
        String LN = "Jogger";
        String NUM = "123311";
        String ADD = "2020 Ave P";

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Contact c = new Contact(ID, FN, LN, NUM, ADD);
        });
    }

    @Test
    public void testShortInputOnName() {
        String ID = "1";
        String FN = "";
        String LN = "Jogger";
        String NUM = "123311";
        String ADD = "2020 Ave P";

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Contact c = new Contact(ID, FN, LN, NUM, ADD);
        });
    }

    @Test
    public void testLongInputOnName() {
        String ID = "1";
        String FN = "Garfield the galactic consumer";
        String LN = "Jogger";
        String NUM = "123311";
        String ADD = "2020 Ave P";

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Contact c = new Contact(ID, FN, LN, NUM, ADD);
        });
    }

    @Test
    public void testNullInputOnLastName() {
        String ID = "1";
        String FN = "Nathan";
        String LN = null;
        String NUM = "123311";
        String ADD = "2020 Ave P";

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Contact c = new Contact(ID, FN, LN, NUM, ADD);
        });
    }

    @Test
    public void testShortInputOnLastName() {
        String ID = "1";
        String FN = "Nathan";
        String LN = "";
        String NUM = "123311";
        String ADD = "2020 Ave P";

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Contact c = new Contact(ID, FN, LN, NUM, ADD);
        });
    }

    @Test
    public void testLongInputOnLastName() {
        String ID = "1";
        String FN = "Nathan";
        String LN = "asdasdasdasdasdasdasdasdasd";
        String NUM = "123311";
        String ADD = "2020 Ave P";

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Contact c = new Contact(ID, FN, LN, NUM, ADD);
        });
    }

    @Test
    public void testNullInputOnNum() {
        String ID = "1";
        String FN = "Nathan";
        String LN = "Cool";
        String NUM = null;
        String ADD = "2020 Ave P";

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Contact c = new Contact(ID, FN, LN, NUM, ADD);
        });
    }

    @Test
    public void testShortInputOnNum() {
        String ID = "1";
        String FN = "Nathan";
        String LN = "Cool";
        String NUM = "";
        String ADD = "2020 Ave P";

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Contact c = new Contact(ID, FN, LN, NUM, ADD);
        });
    }

    @Test
    public void testLongInputOnNum() {
        String ID = "1";
        String FN = "Nathan";
        String LN = "Cool";
        String NUM = "1233111332115151114";
        String ADD = "2020 Ave P";

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Contact c = new Contact(ID, FN, LN, NUM, ADD);
        });
    }

    @Test
    public void testNullInputOnAddress() {
        String ID = "1";
        String FN = "Nathan";
        String LN = "Cool";
        String NUM = "11122";
        String ADD = null;

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Contact c = new Contact(ID, FN, LN, NUM, ADD);
        });
    }

    @Test
    public void testShortInputOnAddress() {
        String ID = "1";
        String FN = "Nathan";
        String LN = "Cool";
        String NUM = "13311";
        String ADD = "";

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Contact c = new Contact(ID, FN, LN, NUM, ADD);
        });
    }

    @Test
    public void testLongInputOnAddress() {
        String ID = "1";
        String FN = "Nathan";
        String LN = "Cool";
        String NUM = "13311";
        String ADD = "2020 Go Here Then There Then Here Then Left Then Right Then Up And Down Rd.";

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Contact c = new Contact(ID, FN, LN, NUM, ADD);
        });
    }
}
