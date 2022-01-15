package com.cds;

import com.cds.controller.Controller;
import lombok.extern.java.Log;

import javax.swing.*;

@Log
public class CashDeskSimulator {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            new Controller().control();
        } catch (Exception e) {
            log.severe(e.getMessage());
        }
    }
}
