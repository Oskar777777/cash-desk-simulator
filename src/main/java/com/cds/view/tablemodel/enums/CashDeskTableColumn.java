package com.cds.view.tablemodel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum CashDeskTableColumn {
    COL_NO(0), COL_NUMBER_OF_CLIENTS(1), COL_INFO(2);

    private final Integer value;

    public static CashDeskTableColumn getByIndex(Integer index) {
        return Arrays.stream(CashDeskTableColumn.values())
                .filter(e -> e.getValue().equals(index)).findFirst().orElse(null);
    }
}
