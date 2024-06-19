import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactServiceTest {
    private ContactService contactService;
    ArrayList<Contact> contacts = new ArrayList<>();
    private InputStream originalSystemIn;

    @Before
    public void setUp() {
        // Initialize the contact service before each test
        contactService = new ContactService();
        originalSystemIn = System.in;
        // You can initialize any required resources here
    }

    @Test
    public void runContactTest() {
        ContactTest contactTest = new ContactTest();
        contactTest.runAllTests();
    }

    @Test
    public void testAddContact() {
        String input = "John\nDoe\n1234567890\n123 Main St\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scnr = new Scanner(System.in);
        Contact contact = ContactService.addNewContact(scnr, contacts);

        assertNotNull(contact);
        assertEquals("John", contact.getFirstName());
        assertEquals("Doe", contact.getLastName());
        assertEquals("1234567890", contact.getNumber());
        assertEquals("123 Main St", contact.getAddress());
    }

    @Test
    public void testDeleteContact() {
        String input = "John\nDoe\n1234567890\n123 Main St\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scnr = new Scanner(System.in);
        Contact contact = ContactService.addNewContact(scnr, contacts);
        contacts.add(contact);

        ContactService.deleteContactViaID(contacts, "1");
        assertNotNull(contacts);
        assertEquals(0, contacts.size());
    }

    @Test
    public void testUpdateContact() {
        String input = "John\nDoe\n1234567890\n123 Main St\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scnr = new Scanner(System.in);
        Contact contact = ContactService.addNewContact(scnr, contacts);
        contacts.add(contact);
        scnr.close();

        // Reset System.in to its original state
        System.setIn(originalSystemIn);

        input = "1\nJohnson\n2\n2\n2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        scnr = new Scanner(System.in);
        ContactService.updateContactViaID(scnr, contacts, "1");

        assertNotNull(contacts);
        assertEquals("Johnson", contacts.get(0).getFirstName());
        assertEquals("Doe", contacts.get(0).getLastName());
        assertEquals("1234567890", contacts.get(0).getNumber());
        assertEquals("123 Main St", contacts.get(0).getAddress());

        // Close the Scanner again
        scnr.close();
    }

}
