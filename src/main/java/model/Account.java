package model;

import service.MonthTransaction;
import utils.Month;

import java.util.HashMap;

public class Account {
    private final String id;
    private final HashMap<Month, MonthTransaction> history;

    public Account(String id) {
        this.id = id;
        this.history = new HashMap<>();
    }

    public void updateByRecord(Record record) {
        Month month = new Month(record.getDay());
        if (!history.containsKey(month)) history.put(month, new MonthTransaction());
        history.get(month).updateByRecord(record);
    }

    public String getId() {
        return id;
    }

    public HashMap<Month, MonthTransaction> getHistory() {
        return history;
    }

    @Override
    public String toString() {
        return "model.Account{" +
                "id='" + id + '\'' +
                ", history=" + history +
                '}';
    }
}
