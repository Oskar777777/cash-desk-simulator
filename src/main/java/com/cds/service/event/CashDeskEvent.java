package com.cds.service.event;

import com.cds.model.Shop;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import java.util.Random;

@Log
@AllArgsConstructor
public class CashDeskEvent extends Thread {
    private volatile Shop shop;

    @Override
    public void run() {
        try {
            startSimulation();
        } catch (InterruptedException e) {
            log.severe(e.getMessage());
        }
    }

    private void startSimulation() throws InterruptedException {
        while(true) {
            if(shop.getClients().size() > 5) {
                long begin = System.currentTimeMillis();
                log.info(String.format("[CashDesk] Client is being handled: %s",
                        shop.getClients().get(shop.getClients().size() - 1).getName()));
                sleep(new Random().nextLong(2000L));
                long finish = System.currentTimeMillis();

                log.info(String.format("[CashDesk] Elapsed time: %d", finish - begin));
                shop.getClients().remove(shop.getClients().size() - 1);

                log.info(String.format("[CashDesk] Clients: %d", shop.getClients().size()));
            }
        }
    }
}
