package utils;

import java.util.Objects;

public class Month {
    private final String year;
    private final String month;

    public Month(Day day) {
        this.year = day.getYear();
        this.month = day.getMonth();
    }

    @Override
    public String toString() {
        return month + "/" + year;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Month other = (Month) obj;
        return this.year.equals(other.year) &&
                this.month.equals(other.month);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month);
    }
}
