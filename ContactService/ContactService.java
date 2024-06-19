import java.util.ArrayList;
import java.util.Scanner;

public class ContactService {
    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);
        ArrayList<Contact> contacts = new ArrayList<>();

        String option = "-1";

        while (!option.equals("0")) {
            System.out.println("What would you like to do?");
            System.out.println("1: Add New Contact");
            System.out.println("2: See All Contacts");
            System.out.println("3: Delete Contact via Contact ID");
            System.out.println("4: Update Contact via Contact ID");
            System.out.println("0: Exit Program");
            System.out.print("Enter Option Number: >>");
            option = scnr.nextLine();

            switch (option) {

                case "1":

                    contacts.add(addNewContact(scnr, contacts));
                    System.out.println("\nContact Added Successfully\n");

                    break;

                case "2":

                    showAllContacts(contacts);

                    break;

                case "3":

                    String ID;
                    System.out.println("Enter the ID of the contact you wish to remove: >>");
                    ID = scnr.nextLine();
                    while (validatePhoneNumber10(ID)) {
                        System.out.println("Enter a valid integer ID no longer than 10 characters: >>");
                        ID = scnr.nextLine();
                    }

                    deleteContactViaID(contacts, ID);

                    break;

                case "4":

                    String id;
                    System.out.println("Enter the ID of the contact you wish to update: >>");
                    id = scnr.nextLine();
                    while (validatePhoneNumber10(id)) {
                        System.out.println("Enter a valid integer ID no longer than 10 characters: >>");
                        id = scnr.nextLine();
                    }

                    updateContactViaID(scnr, contacts, id);

                    break;

                case "0":

                    break;

                default:

                    System.out.println("\nThat is not one of the options.\n");

                    break;

            }
        }
    }

    public static void showAllContacts(ArrayList<Contact> contacts) {
        if (contacts.size() == 0) {
            System.out.println("\nYou have no contacts.\n");
        } else {
            System.out.println(); //Extra souts make new lines.
            for (Contact c: contacts) {
                System.out.println(c.toString() + "\n");
            }
        }
    }

    public static Contact addNewContact(Scanner scnr, ArrayList<Contact> contacts) {
        String ID = contacts.size() + 1 + "";
        String fn;
        String ln;
        String num;
        String add;

        System.out.println("Enter the first name, with a maximum of ten letters: >>");
        fn = scnr.nextLine();
        while (fieldValidate10(fn)) {
            System.out.println("Enter a valid, non empty first name, with a maximum of ten letters: >>");
            fn = scnr.nextLine();
        }

        System.out.println("Enter the last name, with a maximum of ten letters: >>");
        ln = scnr.nextLine();
        while (fieldValidate10(ln)) {
            System.out.println("Enter a valid, non empty last name, with a maximum of ten letters: >>");
            ln = scnr.nextLine();
        }

        System.out.println("Enter their phone number, with a maximum of ten characters: >>");
        num = scnr.nextLine();
        while (validatePhoneNumber10(num)) {
            System.out.println("Enter a valid, non empty phone number, with a maximum of ten characters: >>");
            num = scnr.nextLine();
        }

        System.out.println("Enter their address, with a maximum of thirty characters: >>");
        add = scnr.nextLine();
        while (fieldValidate30(add)) {
            System.out.println("Enter a non empty, valid address, with a maximum of thirty characters: >>");
            add = scnr.nextLine();
        }

        Contact c = new Contact(ID, fn, ln, num, add);

        return c;

    }

    public static boolean fieldValidate10(String input) {
        if (input == "" || input == null) {
            return true;
        } else  if (input.length() > 10) {
            return true;
        }
        return false;
    }

    public static boolean validatePhoneNumber10(String input) {
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

    public static boolean fieldValidate30(String input) {
        if (input == "" || input == null) {
            return true;
        } else  if (input.length() > 30) {
            return true;
        }
        return false;
    }

    public static void deleteContactViaID(ArrayList<Contact> contacts, String ID) {
        ArrayList<Contact> c = new ArrayList<>();
        c.addAll(contacts);
        boolean reorganizeTheIDs = false;

        for (Contact contact : c) {
            if (contact.getID().equals(ID)) {
                reorganizeTheIDs = true;
                contacts.remove(contact);
                break;
            }
        }

        if (reorganizeTheIDs) {
            int id = 0;
            for (Contact contact : contacts) {
                id++;
                contact.setID(Integer.toString(id));
            }
        }
    }

    public static void updateContactViaID(Scanner scnr, ArrayList<Contact> contacts, String ID) {
        String fn = "";
        String ln = "";
        String num = "";
        String add = "";
        boolean bs = true;

        for (Contact contact : contacts) {
            if (contact.getID().equals(ID)) {
                bs = false;
                fn = contact.getFirstName();
                ln = contact.getLastName();
                num = contact.getNumber();
                add = contact.getAddress();
                break;
            }
        }

        if (bs) {
            System.out.println("There is no such contact.");
            return;
        }

        String option;
        System.out.println("The current first name is " + fn + ".");
        System.out.println("1: Change It");
        System.out.println("2: Keep It The Same");
        option = scnr.nextLine();
        while (!check1or2(option)) {
            System.out.println("Enter 1 or 2: >>");
            option = scnr.nextLine();
        }

        if (option.equals("1")) {
            System.out.println("Enter the first name, with a maximum of ten letters: >>");
            fn = scnr.nextLine();
            while (fieldValidate10(fn)) {
                System.out.println("Enter a valid, non empty first name, with a maximum of ten letters: >>");
                fn = scnr.nextLine();
            }
        }

        System.out.println("The current last name is " + ln + ".");
        System.out.println("1: Change It");
        System.out.println("2: Keep It The Same");
        option = scnr.nextLine();
        while (!check1or2(option)) {
            System.out.println("Enter 1 or 2: >>");
            option = scnr.nextLine();
        }

        if (option.equals("1")) {
            System.out.println("Enter the last name, with a maximum of ten letters: >>");
            ln = scnr.nextLine();
            while (fieldValidate10(ln)) {
                System.out.println("Enter a valid, non empty last name, with a maximum of ten letters: >>");
                ln = scnr.nextLine();
            }
        }

        System.out.println("The current phone number is " + num + ".");
        System.out.println("1: Change It");
        System.out.println("2: Keep It The Same");
        option = scnr.nextLine();
        while (!check1or2(option)) {
            System.out.println("Enter 1 or 2: >>");
            option = scnr.nextLine();
        }

        if (option.equals("1")) {
            System.out.println("Enter their phone number, with a maximum of ten characters: >>");
            num = scnr.nextLine();
            while (validatePhoneNumber10(num)) {
                System.out.println("Enter a valid, non empty phone number, with a maximum of ten characters: >>");
                num = scnr.nextLine();
            }
        }

        System.out.println("The current address is " + add + ".");
        System.out.println("1: Change It");
        System.out.println("2: Keep It The Same");
        option = scnr.nextLine();
        while (!check1or2(option)) {
            System.out.println("Enter 1 or 2: >>");
            option = scnr.nextLine();
        }

        if (option.equals("1")) {
            System.out.println("Enter their address, with a maximum of thirty characters: >>");
            add = scnr.nextLine();
            while (fieldValidate30(add)) {
                System.out.println("Enter a non empty, valid address, with a maximum of thirty characters: >>");
                add = scnr.nextLine();
            }
        }

        Contact c = new Contact(ID, fn, ln, num, add);

        contacts.set(Integer.parseInt(ID) - 1, c);
    }

    public static boolean check1or2(String input) {
        switch (input) {
            case "1", "2":
                return true;
        }
        return false;
    }

}