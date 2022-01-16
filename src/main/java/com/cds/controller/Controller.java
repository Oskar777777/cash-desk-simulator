package com.cds.controller;

import com.cds.service.EventService;
import com.cds.view.MainForm;

public class Controller {
    private MainForm mainForm;
    private EventService dispatcher;

    public Controller() {
    }

    public void control() {
        initialize();
    }

    private void initialize() {
        mainForm = new MainForm();
        dispatcher = new EventService();
        mainForm.getFrame().setVisible(true);

        dispatcher.runClientsSimulation();
        dispatcher.runCashDeskSimulation();
    }
}
