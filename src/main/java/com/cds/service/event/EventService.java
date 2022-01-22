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
public class EventService {
    // Service layer
    private ClientEvent clientEvent;
    private CashDeskEvent cashDeskEvent;

    private CashDeskService cashDeskService = new CashDeskService();

    // Model layer
    @Getter
    /* shared resource between threads */
    private Shop shop = new Shop();

    private int K = 3;

    public EventService() {
        /* Client population handler */
        clientEvent = new ClientEvent(shop);

        init();
        handleCashDesks();
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
            log.info("shop.getClients().size() > cashDeskService.countOpenedCashDesks() * K " + shop.getClients().size()
                    + " " + cashDeskService.countOpenedCashDesks() * K);
            if (shop.getClients().size() > cashDeskService.countOpenedCashDesks() * K) {
                CashDesk openedCashDesk = cashDeskService.openFirstAvailableCashDesk();
                shop.setCashDesks(cashDeskService.getCashDesks());
                if (Objects.nonNull(openedCashDesk)) { // if available
                    cashDeskEvent = new CashDeskEvent(shop, openedCashDesk.getNumber());
                    openedCashDesk.setCashDeskEvent(cashDeskEvent);
                }
            } else {
                CashDesk closedCashDesk = cashDeskService.closeFirstOpenedCashDesk();
                if (Objects.nonNull(closedCashDesk)) {
                    closedCashDesk.getCashDeskEvent().exit();
                    cashDeskService.closeCashDesk(closedCashDesk);
                }
            }
        }
    }

    public void runClientsSimulation() {
        clientEvent.start();
    }

    public void runCashDeskSimulation() {
        cashDeskEvent.start();
    }

    public void attach(Observer observer) {
        clientEvent.attach(observer);
        cashDeskEvent.attach(observer);
    }
}
