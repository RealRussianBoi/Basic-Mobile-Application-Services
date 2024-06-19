import static org.junit.Assert.*;
import org.junit.Test;

import java.text.ParseException;

public class AppointmentTest {

    @Test
    public void runAllTests() throws ParseException {
        testGettersAndSetters();
        testNullInputOnID();
        testShortInputOnID();
        testLongInputOnID();
        testNullInputOnDate();
        testEarlyAppointmentDate();
        testNullInputOnDesc();
        testShortInputOnDesc();
        testLongInputOnDesc();
    }

    @Test
    public void testGettersAndSetters() throws ParseException {
        String appointmentID = "1";
        String appointmentDate = "06/22/24";
        String appointmentDescription = "Jogging 5 miles across manhattan.";

        //Test object creation.
        Appointment appointment = new Appointment(appointmentID, appointmentDate, appointmentDescription);

        //Test getters.
        assertEquals(appointmentID, appointment.getID());
        assertEquals(appointmentDate, appointment.getDate());
        assertEquals(appointmentDescription, appointment.getDescription());

        //Test setters and getters.
        String newAppointmentID = "2";
        String newAppointmentDate = "6/22/24";
        String newAppointmentDescription = "Jogging even more.";

        appointment.setID(newAppointmentID);
        appointment.setDate(newAppointmentDate);
        appointment.setDescription(newAppointmentDescription);

        assertEquals(newAppointmentID, appointment.getID());
        assertEquals(appointment.convertToLocalDateViaInstant(appointment.validateDateAfterToday(newAppointmentDate)), appointment.getDate());
        assertEquals(newAppointmentDescription, appointment.getDescription());
    }

    @Test
    public void testNullInputOnID() {
        String appointmentID = null;
        String appointmentDate = "6/22/24";
        String appointmentDescription = "Jogging 5 miles across manhattan.";

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Appointment a = new Appointment(appointmentID, appointmentDate, appointmentDescription);
        });
    }

    @Test
    public void testShortInputOnID() {
        String appointmentID = "";
        String appointmentDate = "6/22/24";
        String appointmentDescription = "Jogging 5 miles across manhattan.";

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Appointment a = new Appointment(appointmentID, appointmentDate, appointmentDescription);
        });
    }

    @Test
    public void testLongInputOnID() {
        String appointmentID = "111111111111111111111";
        String appointmentDate = "6/22/24";
        String appointmentDescription = "Jogging 5 miles across manhattan.";

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Appointment a = new Appointment(appointmentID, appointmentDate, appointmentDescription);
        });
    }

    @Test
    public void testNullInputOnDate() {
        String appointmentID = "1";
        String appointmentDate = null;
        String appointmentDescription = "Jogging 5 miles across manhattan.";

        Throwable exception = assertThrows(NullPointerException.class, () -> {
            Appointment a = new Appointment(appointmentID, appointmentDate, appointmentDescription);
        });
    }

    @Test
    public void testEarlyAppointmentDate() {
        String appointmentID = "1";
        String appointmentDate = "6/2/24";
        String appointmentDescription = "Jogging 5 miles across manhattan.";

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Appointment a = new Appointment(appointmentID, appointmentDate, appointmentDescription);
        });
    }

    @Test
    public void testNullInputOnDesc() {
        String appointmentID = "1";
        String appointmentDate = "6/22/24";
        String appointmentDescription = null;

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Appointment a = new Appointment(appointmentID, appointmentDate, appointmentDescription);
        });
    }

    @Test
    public void testShortInputOnDesc() {
        String appointmentID = "1";
        String appointmentDate = "6/22/24";
        String appointmentDescription = "";

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Appointment a = new Appointment(appointmentID, appointmentDate, appointmentDescription);
        });
    }

    @Test
    public void testLongInputOnDesc() {
        String appointmentID = "1";
        String appointmentDate = "6/22/24";
        String appointmentDescription = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Appointment a = new Appointment(appointmentID, appointmentDate, appointmentDescription);
        });
    }

}

