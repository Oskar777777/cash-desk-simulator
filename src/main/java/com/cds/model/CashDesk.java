package com.cds.model;

import com.cds.model.enums.CashDeskState;
import lombok.Getter;

import java.util.List;

@Getter
public class CashDesk {
    private Integer number;
    private List<Client> clients;
    private CashDeskState state;
}
