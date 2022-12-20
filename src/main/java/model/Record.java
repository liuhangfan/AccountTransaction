package model;

import utils.Day;

import java.util.Optional;

public class Record {
    private final String customerId;
    private final Day day;
    private final Long amount;

    public Record(String customerId, String time, String amount) throws Exception {
        if (customerId.length() == 0) throw new Exception("invalid customerId");
        this.customerId = customerId;
        this.day = new Day(time);
        this.amount = Long.parseLong(amount);
    }

    /**
     * Creates a new Record object with the given customerId, time, and amount.
     *
     * @param customerId the customer id of the new record
     * @param time       the time of the new record
     * @param amount     the amount of the new record
     * @return an Optional containing the new Record object, or empty if an exception is thrown
     */
    public static Optional<Record> newRecord(String customerId, String time, String amount) {
        try {
            return Optional.of(new Record(customerId, time, amount));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public String getCustomerId() {
        return customerId;
    }

    public Day getDay() {
        return day;
    }

    public Long getAmount() {
        return amount;
    }


    @Override
    public String toString() {
        return "model.Record{" +
                "customerId='" + customerId + '\'' +
                ", day=" + day +
                ", amount=" + amount +
                '}';
    }
}
