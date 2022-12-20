package service;

import model.Record;
import utils.Day;

import java.util.Comparator;
import java.util.TreeMap;

public class MonthTransaction {
    private final TreeMap<Day, Long> transactions;

    public MonthTransaction() {
        transactions = new TreeMap<>(Comparator.comparing(Day::getDay));
    }

    /**
     * Updates the transaction records for a given day by adding the given record's amount.
     * If no transaction records exist for the given day, it creates a new entry in the transactions map.
     *
     * @param record the record to be added to the transaction records for a given day
     */
    public void updateByRecord(Record record) {
        Day day = record.getDay();
        Long amount = transactions.getOrDefault(day, 0L);
        amount += record.getAmount();
        transactions.put(day, amount);
    }

    @Override
    public String toString() {
        return "service.MonthTransaction{" +
                "transactions=" + transactions +
                '}';
    }

    /**
     * This method counts the minimum, maximum, and current values in the transactions list.
     *
     * @return a Count object containing the minimum, maximum, and current values in the transactions list
     */
    public Count count() {
        Long cur = 0L;
        Long min = Long.MAX_VALUE;
        Long max = Long.MIN_VALUE;

        for (Long amount : transactions.values()) {
            cur += amount;
            min = Math.min(min, cur);
            max = Math.max(max, cur);
        }
        return new Count(min, max, cur);
    }
}


class Count {
    public Long minBalance;
    public Long maxBalance;
    public Long endingBalance;

    public Count(Long minBalance, Long maxBalance, Long endingBalance) {
        this.minBalance = minBalance;
        this.maxBalance = maxBalance;
        this.endingBalance = endingBalance;
    }

    @Override
    public String toString() {
        return "service.Count{" +
                "minBalance=" + minBalance +
                ", maxBalance=" + maxBalance +
                ", endingBalance=" + endingBalance +
                '}';
    }
}