import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Appointment {
    private String ID;
    private Date date;
    private String Description;

    // Constructor
    public Appointment(String ID, String date, String Description) throws ParseException {
        throwError("ID", checkBad(ID, 10));
        throwError("Description", checkBad(Description, 50));
        this.ID = ID;
        this.date = validateDateAfterToday(date);
        this.Description = Description;
    }

    // Getters
    public String getID() {
        return ID;
    }

    public String getDate() {
        return convertToLocalDateViaInstant(date);
    }

    public String getDescription() {
        return Description;
    }

    // Setters
    public void setID(String ID) {
        this.ID = ID;
    }

    public void setDate(String date) throws ParseException {
        this.date = validateDateAfterToday(date);
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    //Validators.
    public Date validateDateAfterToday(String date) throws ParseException {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");

        Date parsedDate;
        try {
            parsedDate = sdf.parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Date is incorrectly constructed.");
        }

        //This gets rid of the time part of the date.
        String formattedToday = sdf.format(today);
        String formattedDate = sdf.format(parsedDate);

        // Convert the strings back to dates.
        try {
            Date todayWithoutTime = sdf.parse(formattedToday);
            Date providedDateWithoutTime = sdf.parse(formattedDate);

            if (providedDateWithoutTime.before(todayWithoutTime)) {
                throw new IllegalArgumentException("Provided date is earlier than today.");
            }

            return providedDateWithoutTime;
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error during parsing.");
        }
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

    public static String convertToLocalDateViaInstant(Date dateToConvert) {
        LocalDate localDate = dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        return localDate.format(formatter);
    }

    @Override
    public String toString() {
        return "Appointment Details: {" +
                "ID: '" + ID + '\'' +
                ", Date: '" + convertToLocalDateViaInstant(date) + '\'' +
                ", Description: '" + Description + '\'' +
                '}';
    }
}
