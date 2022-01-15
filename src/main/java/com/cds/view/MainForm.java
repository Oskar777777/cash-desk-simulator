package com.cds.view;

import lombok.*;

import javax.swing.*;

@Getter
public class MainForm {
    private JFrame frame;
    private JPanel mainPanel;
    private JTextField textField1;
    private JButton btnAccept;

    public MainForm() {
        frame = new JFrame("MainForm");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }
}
