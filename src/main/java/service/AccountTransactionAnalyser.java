package service;

import model.Account;
import model.AnalysisResult;
import model.Record;
import utils.Month;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountTransactionAnalyser {
    /**
     * Performs analysis on a given input file containing financial records and writes the results to an output file.
     * <p>
     * The input file is expected to be a CSV file with the following columns:
     * - customerId: a unique identifier for the customer
     * - date: the date of the record, in the format "MM/DD/YYYY"
     * - amount: the amount of the transaction
     * <p>
     * The output file will be a CSV file with the following columns:
     * - customerId: the unique identifier of the customer
     * - month: the month of the analysis, in the format "MM/YYYY"
     * - minBalance: the minimum balance of the customer during the month
     * - maxBalance: the maximum balance of the customer during the month
     * - endingBalance: the ending balance of the customer at the end of the month
     *
     * @param inputPath  the path to the input CSV file
     * @param outputPath the path to the output CSV file
     * @throws FileNotFoundException if the input file is not found
     * @throws IOException           if there is an error reading or writing to the input or output file
     */
    public void analysis(String inputPath, String outputPath) {
        CSVService csvService = new CSVService();
        List<Record> records = new ArrayList<>();
        try {
            records = csvService.read(inputPath);
        } catch (Exception e) {
            System.out.println("invalid input file");
            System.out.println(e);
        }
        HashMap<String, Account> accountHashMap = new HashMap<>();
        for (Record record : records) {
            accountHashMap.putIfAbsent(record.getCustomerId(), new Account(record.getCustomerId()));
            accountHashMap.get(record.getCustomerId()).updateByRecord(record);
        }
        List<AnalysisResult> analysisResults = new ArrayList<>();
        for (Account account : accountHashMap.values()) {
            for (Map.Entry<Month, MonthTransaction> entry : account.getHistory().entrySet()) {
                Count count = entry.getValue().count();
                analysisResults.add(new AnalysisResult(
                        account.getId(),
                        entry.getKey().toString(),
                        count.minBalance,
                        count.maxBalance,
                        count.endingBalance));
            }
        }
        try {
            csvService.write(outputPath, analysisResults);
        } catch (Exception e) {
            System.out.println("invalid output file");
            System.out.println(e);
        }
    }
}