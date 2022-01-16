package com.cds.service.event;

import com.cds.model.Client;
import com.cds.model.Shop;
import lombok.extern.java.Log;

import java.util.Random;

@Log
public class ClientEvent extends Thread {
    private Shop shop;

    //TODO: To be removed
    private int clientNo = 0;

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
            sleep(new Random().nextLong(5000L));
            long finish = System.currentTimeMillis();

            log.info(String.format("[Client] Elapsed time: %d", finish - begin));
            shop.getClients().add(Client.builder().name("Client no: " + (++clientNo)).build());

            log.info(String.format("[Client] Clients: %d", shop.getClients().size()));
        }
    }
}
