package ru.vsu.cs.course1;

import com.company.util.SwingUtils;

import java.util.Locale;



public class Program {
    public static void main(String[] args) throws Exception {
        //SwingUtils.setLookAndFeelByName("Windows");
        Locale.setDefault(Locale.ROOT);
        try {
            SwingUtils.setDefaultFont("Microsoft Sans Serif", 18);
            java.awt.EventQueue.invokeLater(() -> new Frame().setVisible(true));

        } catch (Exception e) {
            System.err.print(e.getMessage());
        }
    }
}
