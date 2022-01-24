package com.cds.service.event.events;

import com.cds.model.Shop;
import com.cds.service.observer.Observable;
import com.cds.service.observer.Observer;
import lombok.extern.java.Log;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Log
public class CashDeskEvent extends Thread implements Observable {
    private volatile Shop shop;
    private volatile boolean exit = Boolean.FALSE;
    private int id;

    private Set<Observer> observers = new HashSet<>();

    public CashDeskEvent(Shop shop, int id) {
        this.shop = shop;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            startSimulation();
        } catch (InterruptedException e) {
            log.severe(e.getMessage());
        }
    }

    private void startSimulation() throws InterruptedException {
        while (!exit) {
            if (shop.getClients().size() > 5) {
                long begin = System.currentTimeMillis();
                log.info(String.format("[CashDesk] Client is being handled: %s",
                        shop.getClients().get(shop.getClients().size() - 1).getName()));
                sleep(new Random().nextLong(3000L)); // Client handle time
                long finish = System.currentTimeMillis();

                log.info(String.format("[CashDesk] Elapsed time: %d", finish - begin));
                shop.getClients().remove(shop.getClients().size() - 1);
                notifyObservers();

                log.info(String.format("[CashDesk] Clients: %d", shop.getClients().size()));
            }
        }
    }

    public void exit() {
        exit = Boolean.TRUE;
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(Observer::update);
    }
}
