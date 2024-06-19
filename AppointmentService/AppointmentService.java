import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class AppointmentService {
    public static void main(String[] args) throws ParseException {

        Scanner scnr = new Scanner(System.in);
        ArrayList<Appointment> appointments = new ArrayList<>();

        String option = "-1";

        while (!option.equals("0")) {
            System.out.println("What would you like to do?");
            System.out.println("1: Add New Appointment");
            System.out.println("2: See All Appointments");
            System.out.println("3: Delete Appointment via appointment ID");
            System.out.println("4: Update Appointment via Appointment ID");
            System.out.println("0: Exit Program");
            System.out.print("Enter Option Number: >>");
            option = scnr.nextLine();

            switch (option) {

                case "1":

                    appointments.add(addNewAppointment(scnr, appointments));

                    break;

                case "2":

                    showAllAppointments(appointments);

                    break;

                case "3":

                    String ID;
                    System.out.println("Enter the ID of the appointment you wish to remove: >>");
                    ID = scnr.nextLine();
                    while (validateNumber10(ID)) {
                        System.out.println("Enter a valid integer ID no longer than 10 characters: >>");
                        ID = scnr.nextLine();
                    }

                    deleteAppointmentViaID(appointments, ID);

                    break;

                case "4":

                    String id;
                    System.out.println("Enter the ID of the appointment you wish to update: >>");
                    id = scnr.nextLine();
                    while (validateNumber10(id)) {
                        System.out.println("Enter a valid integer ID no longer than 10 characters: >>");
                        id = scnr.nextLine();
                    }

                    updateAppointmentViaID(scnr, appointments, id);

                    break;

                case "0":

                    break;

                default:

                    System.out.println("\nThat is not one of the options.\n");

                    break;

            }
        }
    }

    public static void showAllAppointments(ArrayList<Appointment> list) {
        if (list.size() == 0) {
            System.out.println("\nYou have no appointments.\n");
        } else {
            System.out.println(); //Extra souts make new lines.
            for (Appointment l: list) {
                System.out.println(l.toString() + "\n");
            }
        }
    }

    public static Appointment addNewAppointment(Scanner scnr, ArrayList<Appointment> list) throws ParseException {
        String ID = list.size() + 1 + "";
        String Date;
        String Desc;

        System.out.println("Enter the appointment date in MM/DD/YYYY format. The date cannot be earlier than today: >>");
        Date = scnr.nextLine();
        while (theProvidedDateIsEarlierThanToday(Date)) {
            System.out.println("Enter a valid appointment date in MM/DD/YYYY format that is not earlier than today: >>");
            Date = scnr.nextLine();
        }

        System.out.println("Enter the appointment's description, with a maximum length of fifty letters: >>");
        Desc = scnr.nextLine();
        while (fieldValidate50(Desc)) {
            System.out.println("Enter a valid, non empty appointment description, with a maximum of fifty letters: >>");
            Desc = scnr.nextLine();
        }

        Appointment a = new Appointment(ID, Date, Desc);

        System.out.println("\nAppointment recorded successfully. Appointment contents: " + a + "\n"); //To create a gap between the end of this function and menu.

        return a;
    }

    public static void deleteAppointmentViaID(ArrayList<Appointment> list, String ID) {
        boolean reorganizeTheIDs = false;

        for (Appointment l : list) {
            if (l.getID().equals(ID)) {
                reorganizeTheIDs = true;
                list.remove(l);
                break;
            }
        }

        if (reorganizeTheIDs) {
            int id = 0;
            for (Appointment l : list) {
                id++;
                l.setID(Integer.toString(id));
            }

            System.out.println("\nSuccessfully deleted appointment.\n"); //To create a gap between the end of this function and menu.

        } else {
            System.out.println("\nNo such appointment exists.\n");
        }
    }

    public static void updateAppointmentViaID(Scanner scnr, ArrayList<Appointment> list, String ID) throws ParseException {
        String Date = "";
        String Desc = "";
        boolean bs = true;
        boolean updated = false;

        for (Appointment l : list) {
            if (l.getID().equals(ID)) {
                bs = false;
                Date = l.getDate();
                Desc = l.getDescription();
                break;
            }
        }

        if (bs) {
            System.out.println("\nThere is no such appointment.\n");
            return;
        }

        String option;
        System.out.println("The appointment's date is: >>" + Date + ".");
        System.out.println("1: Change Date");
        System.out.println("2: Keep Date");
        option = scnr.nextLine();
        while (!check1or2(option)) {
            System.out.println("Enter 1 or 2: >>");
            option = scnr.nextLine();
        }

        if (option.equals("1")) {
            System.out.println("Enter the appointment's new date in MM/DD/YY format. The new date cannot be earlier than today: >>");
            Date = scnr.nextLine();
            while (theProvidedDateIsEarlierThanToday(Date)) {
                System.out.println("Enter a valid, non empty appointment date in MM/DD/YY format. The new date cannot be earlier than today: >>");
                Date = scnr.nextLine();
            }
            updated = true;
        }

        System.out.println("The appointment's description is: >>" + Desc + ".");
        System.out.println("1: Change Description");
        System.out.println("2: Keep Description");
        option = scnr.nextLine();
        while (!check1or2(option)) {
            System.out.println("Enter 1 or 2: >>");
            option = scnr.nextLine();
        }

        if (option.equals("1")) {
            System.out.println("Enter the appointment description, with a maximum length of ten letters: >>");
            Desc = scnr.nextLine();
            while (fieldValidate50(Desc)) {
                System.out.println("Enter a valid, non empty appointment description, with a maximum length of fifty letters: >>");
                Desc = scnr.nextLine();
            }
            updated = true;
        }

        Appointment a = new Appointment(ID, Date, Desc);
        list.set(Integer.parseInt(ID) - 1, a);

        if (updated) {
            System.out.println("\nAppointment updated successfully. New appointment contents: " + a + "\n");
        } else {
            System.out.println("\nNothing was updated.\n");
        }
    }

    public static boolean fieldValidate50(String input) {
        if (input == "" || input == null) {
            return true;
        } else  if (input.length() > 50) {
            return true;
        }
        return false;
    }

    public static boolean validateNumber10(String input) {
        if (input == "" || input == null) {
            return true;
        } else  if (input.length() > 10) {
            return true;
        }

        try {
            Integer.parseInt(input);
        } catch (Exception e) {
            return true;
        }

        return false;
    }

    public static boolean check1or2(String input) {
        switch (input) {
            case "1", "2":
                return true;
        }
        return false;
    }

    public static boolean theProvidedDateIsEarlierThanToday(String date) throws ParseException {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");

        Date parsedDate;
        try {
            parsedDate = sdf.parse(date);
        } catch (ParseException e) {
            return true;
        }

        //This gets rid of the time part of the date.
        String formattedToday = sdf.format(today);
        String formattedDate = sdf.format(parsedDate);

        // Convert the strings back to dates.
        try {
            Date todayWithoutTime = sdf.parse(formattedToday);
            Date providedDateWithoutTime = sdf.parse(formattedDate);

            if (providedDateWithoutTime.before(todayWithoutTime)) {
                return true;
            }

            return false;
        } catch (ParseException e) {
            e.printStackTrace();
            return true;
        }
    }
}