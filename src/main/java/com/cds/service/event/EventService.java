package com.cds.service.event;

import com.cds.model.CashDesk;
import com.cds.model.Shop;
import com.cds.service.cashdesk.CashDeskService;
import com.cds.service.event.events.CashDeskEvent;
import com.cds.service.event.events.ClientEvent;
import com.cds.service.observer.Observer;
import lombok.Getter;
import lombok.extern.java.Log;

import java.util.Objects;

@Log
public class EventService extends Thread {
    // Service layer
    private ClientEvent clientEvent;
    private CashDeskEvent cashDeskEvent;

    private CashDeskService cashDeskService; // = new CashDeskService(); //TODO: added - TBC

    // Model layer
    @Getter
    /* shared resource between threads */
    private Shop shop = new Shop();

    private int K = 3;

    public EventService() {
        clientEvent = new ClientEvent(shop);
        cashDeskService = new CashDeskService(shop.getCashDesks());
        init();
    }

    @Override
    public void run() {
        /* Client population handler */
        handleCashDesks();
        runClientsSimulation();
        runCashDeskSimulation();
    }

    private void init() {
        shop.setCashDesks(cashDeskService.getCashDesks());
        cashDeskService.init().forEach(e -> cashDeskEvent = new CashDeskEvent(shop, e.getNumber()));
    }

    //TODO: run as the new event - management event
    //TODO: properties handling
    //TODO: bug fixing
    private void handleCashDesks() {
        while(true) {
            // log.info("shop.getClients().size() > cashDeskService.countOpenedCashDesks() * K " + shop.getClients().size()
            //         + " " + cashDeskService.countOpenedCashDesks() * K);
             log.info("shop.getClients().size() > cashDeskService.countOpenedCashDesks() * K "
                     + (shop.getClients().size() > cashDeskService.countOpenedCashDesks() * K));
            if (shop.getClients().size() > cashDeskService.countOpenedCashDesks() * K) {
                CashDesk openedCashDesk = cashDeskService.openFirstAvailableCashDesk();
                shop.setCashDesks(cashDeskService.getCashDesks());
                if (Objects.nonNull(openedCashDesk)) { // if available
                    cashDeskEvent = new CashDeskEvent(shop, openedCashDesk.getNumber());
                    openedCashDesk.setCashDeskEvent(cashDeskEvent);
                    log.info("OPENED " + openedCashDesk.getNumber());
                }
            } else {
                CashDesk closedCashDesk = cashDeskService.closeFirstOpenedCashDesk();
                shop.setCashDesks(cashDeskService.getCashDesks()); //TODO: added
                if (Objects.nonNull(closedCashDesk)
                        && Objects.nonNull(closedCashDesk.getCashDeskEvent())) { // if event not exists
                    closedCashDesk.getCashDeskEvent().exit();
                    cashDeskService.closeCashDesk(closedCashDesk);
                    log.info("CLOSED " + closedCashDesk.getNumber());
                }
            }
        }
    }

    public void runClientsSimulation() {
        new Thread(clientEvent).start();
    }

    public void runCashDeskSimulation() {
        new Thread(cashDeskEvent).start();
    }

    public void attach(Observer observer) {
        clientEvent.attach(observer);
        cashDeskEvent.attach(observer);
    }
}
