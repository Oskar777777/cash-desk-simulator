package com.cds.controller;

import com.cds.service.event.EventService;
import com.cds.service.observer.Observer;
import com.cds.view.MainForm;
import com.cds.view.tablemodel.ShopTableModel;
import lombok.extern.java.Log;

@Log
public class Controller implements Observer {
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
        dispatcher.start();
        dispatcher.attach(this);
    }

    @Override
    public void update() {
        // being updated by the CashDeskEvent
        // being updated by the ClientEvent
        log.info("Controller.update() " + dispatcher.getShop().getCashDesks().size());
        ((ShopTableModel) mainForm.getTableShop().getModel()).setCashDesks(dispatcher.getShop().getCashDesks());
        mainForm.getLblNumberOfClients().setText("Number of Clients: " + dispatcher.getShop().getClients().size());
    }
}
