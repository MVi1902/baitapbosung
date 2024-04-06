package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EditorModel {
    private List<String> lines = new ArrayList<>();

    public void addLine(String line) {
        lines.add(line);
    }

    public List<String> getLines() {
        return lines;
    }

//    load file trả về list string
    public List<String> loadFromFile(File file) throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();
        return lines;
    }

//    save file từ list string
    public void saveToFile(List<String> lines, File file) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (String line : lines) {
            writer.write(line);
            writer.newLine();
        }
        writer.close();
    }
}