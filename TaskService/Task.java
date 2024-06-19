public class Task {
    private String ID;
    private String Name;
    private String Description;

    // Constructor
    public Task(String ID, String Name, String Description) {
        throwError("ID", checkBad(ID, 10));
        throwError("Name", checkBad(Name, 20));
        throwError("Description", checkBad(Description, 50));
        this.ID = ID;
        this.Name = Name;
        this.Description = Description;
    }

    // Getters
    public String getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    // Setters
    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setDescription(String Description) {
        this.Description = Description;
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

    @Override
    public String toString() {
        return "Task{" +
                "ID='" + ID + '\'' +
                ", Name='" + Name + '\'' +
                ", Description='" + Description + '\'' +
                '}';
    }
}
