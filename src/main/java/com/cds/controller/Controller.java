package com.cds.controller;

import com.cds.view.MainForm;

public class Controller {
    private MainForm mainForm;

    public Controller() {
    }

    public void control() {
        initialize();
    }

    private void initialize() {
        mainForm = new MainForm();
        mainForm.getFrame().setVisible(true);
    }
}
