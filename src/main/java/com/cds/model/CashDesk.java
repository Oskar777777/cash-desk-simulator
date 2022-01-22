package com.cds.model;

import com.cds.model.enums.CashDeskState;
import com.cds.service.event.events.CashDeskEvent;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Builder
public class CashDesk {
    private Integer number;
    private List<Client> clients;
    @Setter
    private CashDeskState state;
    @Setter
    private CashDeskEvent cashDeskEvent;
}
