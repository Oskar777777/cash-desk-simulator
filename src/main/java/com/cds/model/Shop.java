package com.cds.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Shop {
    private List<Client> clients = new ArrayList<>();
    @Setter
    private List<CashDesk> cashDesks = new ArrayList<>();
}
