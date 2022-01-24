package com.cds.service.event.events;

import com.cds.model.Client;
import com.cds.model.Shop;
import com.cds.service.observer.Observable;
import com.cds.service.observer.Observer;
import lombok.Getter;
import lombok.extern.java.Log;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Log
public class ClientEvent extends Thread implements Observable {
    private Shop shop;

    //TODO: To be removed
    private int clientNo = 0;

    private Set<Observer> observers = new HashSet<>();

    public ClientEvent(Shop shop) {
        this.shop = shop;
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
        while (true) {
            long begin = System.currentTimeMillis();
            sleep(new Random().nextLong(2000L));
            long finish = System.currentTimeMillis();

            log.info(String.format("[Client] Elapsed time: %d", finish - begin));
            shop.getClients().add(Client.builder().name("Client no: " + (++clientNo)).build());
            notifyObservers(); //TODO: added

            log.info(String.format("[Client] Clients: %d", shop.getClients().size()));
        }
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
