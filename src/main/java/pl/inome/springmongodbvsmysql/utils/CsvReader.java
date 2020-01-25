package pl.inome.springmongodbvsmysql.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvReader {

    private String csvFileName;
    private String cvsSplitBy;

    public CsvReader() {
        this.csvFileName = "data.csv";
        this.cvsSplitBy = ";";
    }

    public CsvReader(String csvFileName, String cvsSplitBy) {
        this.csvFileName = csvFileName;
        this.cvsSplitBy = cvsSplitBy;
    }

    public List<List<String>> getRecords() throws Exception {
        List<List<String>> records = new ArrayList<>();
        File file = new File(csvFileName);
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        try {
            String line;
//            bufferedReader.readLine(); // ignore first line
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(cvsSplitBy);
                records.add(Arrays.asList(values));
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

}
