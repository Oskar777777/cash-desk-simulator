package com.cds.service;

import com.cds.model.Shop;
import com.cds.service.event.CashDeskEvent;
import com.cds.service.event.ClientEvent;
import lombok.AllArgsConstructor;

public class EventService {
    // Service layer
    private ClientEvent clientEvent;
    private CashDeskEvent cashDeskEvent;

    // Model layer
    /* shared resource between threads */
    private Shop shop = new Shop();

    public EventService() {
        clientEvent = new ClientEvent(shop);
        cashDeskEvent = new CashDeskEvent(shop);
    }

    public void runClientsSimulation() {
        clientEvent.start();
    }

    public void runCashDeskSimulation() {
        cashDeskEvent.start();
    }
}
