import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class AppointmentServiceTest {
    private AppointmentService appointmentService;
    ArrayList<Appointment> list = new ArrayList<>();
    private InputStream originalSystemIn;

    @Before
    public void setUp() {
        // Initialize the contact service before each test
        appointmentService = new AppointmentService();
        originalSystemIn = System.in;
        // You can initialize any required resources here
    }

    @Test
    public void runAppointmentTest() throws ParseException {
        AppointmentTest appointmentTest = new AppointmentTest();
        appointmentTest.runAllTests();
    }

    @Test
    public void testAddAppointment() throws ParseException {
        String input = "6/22/24\nJogging\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scnr = new Scanner(System.in);
        Appointment a = AppointmentService.addNewAppointment(scnr, list);

        assertNotNull(a);
        assertNotNull(a.getID());
        assertEquals(a.convertToLocalDateViaInstant(a.validateDateAfterToday("6/22/24")), a.getDate());
        assertEquals("Jogging", a.getDescription());
    }

    @Test
    public void testDeleteAppointment() throws ParseException {
        String input = "6/22/24\nJogging\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scnr = new Scanner(System.in);
        Appointment a = AppointmentService.addNewAppointment(scnr, list);
        list.add(a);

        AppointmentService.deleteAppointmentViaID(list, "1");
        assertNotNull(list);
        assertEquals(0, list.size());
    }

    @Test
    public void testUpdateAppointment() throws ParseException {
        String input = "6/22/24\nJogging\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scnr = new Scanner(System.in);
        Appointment a = AppointmentService.addNewAppointment(scnr, list);
        list.add(a);
        scnr.close();

        // Reset System.in to its original state
        System.setIn(originalSystemIn);

        input = "1\n6/23/24\n1\nMewing To Skibbidy.\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        scnr = new Scanner(System.in);
        AppointmentService.updateAppointmentViaID(scnr, list, "1");

        assertNotNull(list);
        assertNotNull(list.get(0).getID());
        assertEquals(a.convertToLocalDateViaInstant(a.validateDateAfterToday("6/23/24")), list.get(0).getDate());
        assertEquals("Mewing To Skibbidy.", list.get(0).getDescription());

        // Close the Scanner again
        scnr.close();
    }

}
