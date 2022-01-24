package com.cds.service.cashdesk;

import com.cds.model.CashDesk;
import com.cds.model.enums.CashDeskState;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class CashDeskService {
    @Getter
    private List<CashDesk> cashDesks = new ArrayList<>();

    public CashDeskService(List<CashDesk> cashDesks) {
        this.cashDesks = cashDesks;
    }

    public List<CashDesk> init() {
        int M = 5;
        for (int i = 0; i < M; i++)
            this.cashDesks.add(CashDesk.builder().state(CashDeskState.CLOSED)
                    .number(i + 1).build());
        return List.of(this.cashDesks.get(0), this.cashDesks.get(1));
    }

    public CashDesk openFirstAvailableCashDesk() {
        CashDesk openedCashDesk = null;
        for (CashDesk cashDesk : cashDesks)
            if ((CashDeskState.CLOSED.equals(cashDesk.getState())
                    || CashDeskState.TO_BE_CLOSED.equals(cashDesk.getState()))) { //TODO: added
                //TODO: removed - countOpenedCashDesks() <= 2 &&
                openedCashDesk = cashDesk;
                cashDesk.setState(CashDeskState.OPEN);
            }
        return openedCashDesk;
    }

    public CashDesk closeFirstOpenedCashDesk() {
        CashDesk closedCashDesk = null;
        for (CashDesk cashDesk : cashDesks)
            if (countOpenedCashDesks() > 2 && CashDeskState.OPEN.equals(cashDesk.getState())) {
                closedCashDesk = cashDesk;
                cashDesk.setState(CashDeskState.TO_BE_CLOSED);
            }
        return closedCashDesk;
    }

    public void closeCashDesk(CashDesk cashDesk) {
        for (CashDesk cd : cashDesks)
            if (cashDesk.getNumber().equals(cd.getNumber()))
                cashDesk.setState(CashDeskState.CLOSED);
    }

    public int countOpenedCashDesks() {
        int counter = 0;
        for (CashDesk cashDesk : cashDesks)
            if (CashDeskState.OPEN.equals(cashDesk.getState()))
                counter++;
        return counter;
    }
}
