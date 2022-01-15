package com.cds.model;

import com.cds.model.enums.CashDeskState;

import java.util.List;

public class CashDesk {
    private Integer number;
    private List<Client> clients;
    private CashDeskState state;
}
