package appRunner.days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public abstract class Day {
    public abstract void solve();

    public LinkedList<String> readLines(String filePath) {
        BufferedReader reader;
        LinkedList<String> lines = new LinkedList<>();

        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }
}
