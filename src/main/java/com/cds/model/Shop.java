package com.cds.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Shop {
    private List<Client> clients = new ArrayList<>();
    private List<CashDesk> cashDesks = new ArrayList<>();
}
