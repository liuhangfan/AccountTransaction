package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Day {
    private final String year;
    private String month;
    private String day;

    /**
     * Constructs a Day object with a given date string.
     *
     * @param dateString a string representation of a date in the format "MM/dd/yyyy"
     * @throws ParseException if the date string cannot be parsed into a valid date
     */
    public Day(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = dateFormat.parse(dateString);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        this.year = String.valueOf(calendar.get(Calendar.YEAR));
        this.month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        this.day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        if (this.month.length() == 1) this.month = "0" + this.month;
        if (this.day.length() == 1) this.day = "0" + this.day;
    }

    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Day other = (Day) obj;
        return Objects.equals(year, other.year) &&
                Objects.equals(month, other.month) &&
                Objects.equals(day, other.day);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, day);
    }
}

