package ru.vsu.cs.course1;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import ru.vsu.cs.course1.MyMap;
import ru.vsu.cs.course1.NotMyMap;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Frame extends JFrame {
    private JPanel mainPanel;
    private JTextArea inputText;
    private JFileChooser fileChooserOpen;
    private JFileChooser fileChooserSave;
    private JTextArea outText;
    private JButton findButton;
    private JButton notMyfindButton;
    private JSpinner nSpinner;
    private JSpinner sSpinner;
    private JButton inputButton;
    private JButton outButton;
    private JPanel outPanel;
    private JScrollPane outScroll;
    private JLabel nLabel;
    private JLabel sLabel;
    private JPanel inputPanel;
    private JScrollPane inScroll;

    public static int workFlag = 0;

    public Frame() {
        this.setTitle("task 6.1");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(1200, 800);

        inputText.setRows(500);
        inputText.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        inputText.setLineWrap(true);

        final String[] text = {""};
        fileChooserOpen = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);

        outText.setRows(500);
        outText.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        outText.setLineWrap(true);

        final String[] textOut = {""};
        final String[] textOut1 = {""};
        fileChooserSave = new JFileChooser();
        fileChooserSave.setCurrentDirectory(new File("."));
        fileChooserSave.addChoosableFileFilter(filter);
        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                text[0] = " ";
                text[0] = inputText.getText();
                String goodOutText = text[0].replaceAll("[0-9.?!)(,:\"“”«©»]+", "");
                int n = (int) nSpinner.getValue();
                int s = (int) sSpinner.getValue();

                textOut1[0] = String.valueOf((MyMap.findingWords(s, n, goodOutText)));
                textOut1[0].replaceAll("^\\[|\\]$", " ");
                if (n == 0 || s == 0) {
                    JOptionPane.showMessageDialog(new JFrame(), "Вы не ввели, сколько нужно найти слов или сколько букв должно быть в этих словах. Пожалуйста попробуйте ещё раз.", "Упс, ошибочка!", JOptionPane.ERROR_MESSAGE);
                }

                if (goodOutText.length() > 1) {
                    outText.setText(textOut1[0]);
                    workFlag = 1;
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Поле ввода пустое. Пожалуйста попробуйте ещё раз.", "Упс, ошибочка!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        notMyfindButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                text[0] = " ";
                text[0] = inputText.getText();
                String goodOutText = text[0].replaceAll("[0-9.?!)(,:\"“”«©»]+", "");
                int n = (int) nSpinner.getValue();
                int s = (int) sSpinner.getValue();

                textOut[0] = String.valueOf((NotMyMap.findingWord(s, n, goodOutText)));
                textOut[0].replaceAll("^\\[|\\]$", " ");
                if (n == 0 || s == 0) {
                    JOptionPane.showMessageDialog(new JFrame(), "Вы не ввели, сколько нужно найти слов или сколько букв должно быть в этих словах. Пожалуйста попробуйте ещё раз.", "Упс, ошибочка!", JOptionPane.ERROR_MESSAGE);
                }
                if (goodOutText.length() > 1) {
                    outText.setText(textOut[0]);
                    workFlag = 1;
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Поле ввода пустое. Пожалуйста попробуйте ещё раз.", "Упс, ошибочка!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        inputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserOpen.showOpenDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {
                        FileReader fr = new FileReader(fileChooserOpen.getSelectedFile().getPath());
                        StringBuffer strBuffer = new StringBuffer();
                        int symbol;
                        while ((symbol = fr.read()) != -1) {
                            strBuffer.append((char) symbol);
                        }
                        inputText.setText(strBuffer.toString());
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Ошибка");
                }
            }
        });
        outButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (workFlag != 0) {
                        if (fileChooserSave.showSaveDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {
                            String file = fileChooserSave.getSelectedFile().getPath();
                            if (!file.toLowerCase().endsWith(".txt")) {
                                file += ".txt";
                            }
                            FileWriter fw = new FileWriter(file);
                            fw.write(textOut[0]);
                            fw.flush();
                            fw.close();
                        }
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Кажется, поле ввода пустое. Пожалуйста попробуйте ещё раз.", "Упс, ошибочка!", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (
                        Exception e) {
                    JOptionPane.showMessageDialog(null, "Ошибка");
                }
            }
        });


    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new FormLayout("fill:d:grow,left:37dlu:noGrow,fill:204px:grow,left:68dlu:noGrow,fill:204px:grow,left:4dlu:noGrow,fill:max(d;4px):noGrow", "center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow"));
        outPanel = new JPanel();
        outPanel.setLayout(new FormLayout("fill:d:grow", "center:d:grow,top:4dlu:noGrow,center:d:grow"));
        CellConstraints cc = new CellConstraints();
        mainPanel.add(outPanel, cc.xyw(1, 7, 7));
        outScroll = new JScrollPane();
        outPanel.add(outScroll, cc.xy(1, 3, CellConstraints.FILL, CellConstraints.FILL));
        outText = new JTextArea();
        outScroll.setViewportView(outText);
        inputButton = new JButton();
        inputButton.setText("Загрузить из файла");
        mainPanel.add(inputButton, cc.xy(1, 5));
        outButton = new JButton();
        outButton.setText("Загрузить в файл");
        mainPanel.add(outButton, cc.xy(1, 9));
        nLabel = new JLabel();
        nLabel.setText("Введите кол-во самых часто встречаемых слов:");
        mainPanel.add(nLabel, cc.xy(1, 1));
        findButton = new JButton();
        findButton.setText("Найти слова с помощью моей реализации");
        mainPanel.add(findButton, cc.xyw(2, 5, 2));
        inputPanel = new JPanel();
        inputPanel.setLayout(new FormLayout("fill:d:grow", "center:d:grow,top:4dlu:noGrow,center:d:grow"));
        mainPanel.add(inputPanel, cc.xyw(1, 3, 7));
        inScroll = new JScrollPane();
        inputPanel.add(inScroll, cc.xy(1, 3, CellConstraints.FILL, CellConstraints.FILL));
        inputText = new JTextArea();
        inScroll.setViewportView(inputText);
        nSpinner = new JSpinner();
        mainPanel.add(nSpinner, cc.xy(2, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        sLabel = new JLabel();
        sLabel.setText("Введите количество букв в этих словах:");
        mainPanel.add(sLabel, cc.xyw(3, 1, 2));
        sSpinner = new JSpinner();
        mainPanel.add(sSpinner, cc.xy(5, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        notMyfindButton = new JButton();
        notMyfindButton.setText("Найти слова с помощью не моей реализации");
        mainPanel.add(notMyfindButton, cc.xyw(4, 5, 2));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
