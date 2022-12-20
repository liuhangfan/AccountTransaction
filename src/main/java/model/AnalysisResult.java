package model;

public class AnalysisResult {
    public String customerID;
    public String month;
    public Long minBalance;
    public Long maxBalance;
    public Long endingBalance;

    public AnalysisResult(String customerID, String month, Long minBalance, Long maxBalance, Long endingBalance) {
        this.customerID = customerID;
        this.month = month;
        this.minBalance = minBalance;
        this.maxBalance = maxBalance;
        this.endingBalance = endingBalance;
    }

    @Override
    public String toString() {
        return "model.AnalysisResult{" +
                "customerID='" + customerID + '\'' +
                ", month='" + month + '\'' +
                ", minBalance='" + minBalance + '\'' +
                ", maxBalance='" + maxBalance + '\'' +
                ", endingBalance='" + endingBalance + '\'' +
                '}';
    }
}
