package View;

import Controller.EditorController;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class EditorView extends JFrame {
    private JTextArea textArea;
    private JPanel mainPanel;
    private JButton luuFile;
    private JButton taiFile;
    private JButton duyetThuMuc;
    private EditorController controller;
    public EditorView() {
        setTitle("Text Editor");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(mainPanel);
        controller = new EditorController(this);
        initListeners();
    }

//    đọc list<string> và duyệt bằng cách sử dụng hàm stream(), sau đó đưa vào text area
    public void displayLines(List<String> lines) {
        textArea.setText("");
        lines.stream().forEach(line -> textArea.append(line + "\n"));
    }

    public void initListeners() {
        taiFile.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                try {
                    controller.loadFile(fileChooser.getSelectedFile());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        luuFile.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                try {
                    controller.saveFile(getLines(), fileChooser.getSelectedFile());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        duyetThuMuc.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                List<String> textList = controller.showDirectories(fileChooser.getSelectedFile().getAbsolutePath(), 0);
                displayLines(textList);
            }
        });
    }

    public List<String> getLines() {
        return List.of(textArea.getText().split("\n"));
    }
}
