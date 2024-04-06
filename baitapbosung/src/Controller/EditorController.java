package Controller;

import Model.EditorModel;
import View.EditorView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EditorController {
    private EditorModel model;
    private EditorView view;

    public EditorController(EditorView view) {
        this.view = view;
        model = new EditorModel();
    }

    public void addLine(String line) {
        model.addLine(line);
    }

    public void loadFile(File file) throws IOException {
        List<String> textList = model.loadFromFile(file);
        view.displayLines(textList);
    }
    public void saveFile(List<String> textList, File file) throws IOException {
        model.saveToFile(textList, file);
    }
    public List<String> showDirectories(String path, int level) {
        List<String> textList = new ArrayList<>();
        File directory = new File(path);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    String indentation = " ".repeat(level);
                    textList.add(indentation + "|-" + " [DIR] " + file.getName());
                    textList.addAll(showDirectories(file.getAbsolutePath(), level + 1));
                } else {
                    String indentation = " ".repeat(level);
                    textList.add(indentation + "|-" + " " + file.getName());
                }
            }
        }
        return textList;
    }
}