package service;

import model.AnalysisResult;
import model.Record;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CSVService {
    private final String splitStr = ",";

    public List<Record> read(String path) throws IOException {
        List<Record> records = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(path));
        for (int i = 0; i < lines.size(); i++) {
            if (i == 0) continue;
            String[] inputs = lines.get(i).split(splitStr);
            if (inputs.length < 3) continue;
            Optional<Record> record = Record.newRecord(inputs[0], inputs[1], inputs[2]);
            record.ifPresent(records::add);
        }
        return records;
    }

    public void write(String path, List<AnalysisResult> analysisResults) throws Exception {
        Path filePath = Paths.get(path);
        BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.APPEND,
                StandardOpenOption.CREATE);
        writer.write("CustomerID,MM/YYYY,MinBalance,MaxBalance,EndingBalance");
        writer.newLine();
        for (AnalysisResult result : analysisResults) {
            String line = result.customerID + splitStr + result.month + splitStr + result.minBalance + splitStr
                    + result.maxBalance + splitStr + result.endingBalance;
            writer.write(line);
            System.out.println(line);
            writer.newLine();
        }
        System.out.println("finish");
        writer.close();
    }
}
