import service.AccountTransactionAnalyser;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;


public class main {
    public static void main(String[] args) {
        Properties props = new Properties();
        try {
            props.load(Files.newInputStream(Paths.get("src/main/resources/application.properties")));
        } catch (Exception e) {
            System.out.println("invalid properties");
        }
        // the location of your csv file
        String inputPath = props.getProperty("inputPath");
        String outputPath = props.getProperty("outputPath");
        AccountTransactionAnalyser accountTransactionAnalyser = new AccountTransactionAnalyser();
        accountTransactionAnalyser.analysis(inputPath, outputPath);
    }
}
