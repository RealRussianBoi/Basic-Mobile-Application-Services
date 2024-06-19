public class Contact {
    private String ID;
    private String firstName;
    private String lastName;
    private String Number;
    private String Address;

    // Constructor
    public Contact(String ID, String firstName, String lastName, String Number, String Address) {
        throwError("ID", checkBad(ID, 10));
        throwError("First Name", checkBad(firstName, 10));
        throwError("Last Name", checkBad(lastName, 10));
        throwError("Phone #", checkBad(Number, 10));
        throwError("Address", checkBad(Address, 30));
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.Number = Number;
        this.Address = Address;
    }

    // Getter and Setter methods
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String Number) {
        this.Number = Number;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    private int checkBad(String input, int maxL) {
        if (input == null) {
            return 1;
        } else if (input.length() == 0 || input.isBlank() || input.isEmpty()) {
            return 2;
        } else if (input.length() > maxL) {
            return 3;
        }
        return -1;
    }

    private void throwError(String fieldName, int num) {
        switch (num) {
            case (-1):
                return;
            case (1):
                throw new IllegalArgumentException(fieldName + " field cannot be null");
            case (2):
                throw new IllegalArgumentException(fieldName + " field cannot be empty");
            case (3):
                throw new IllegalArgumentException(fieldName + " field is too long");
        }
    }

    // toString method for printing the object
    @Override
    public String toString() {
        return "Contact{" +
                "ID='" + ID + '\'' +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", Number='" + Number + '\'' +
                ", Address='" + Address + '\'' +
                '}';
    }
}
