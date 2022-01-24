package com.cds.view.tablemodel;

import com.cds.model.CashDesk;
import com.cds.view.tablemodel.enums.CashDeskTableColumn;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ShopTableModel extends AbstractTableModel {
    private List<CashDesk> cashDesks = new ArrayList<>();
    private String[] columns;

    public ShopTableModel() {
        super();
        this.columns = new String[]{"No", "Number of Clients", "Info"};
    }

    public void add(CashDesk cashDesk) {
        cashDesks.add(cashDesk);
        this.fireTableDataChanged();
    }

    public void setCashDesks(List<CashDesk> cashDesks) {
        this.cashDesks = cashDesks;
        this.fireTableDataChanged();
    }

    public CashDesk getCashDesk(int index) {
        return cashDesks.get(index);
    }

    @Override
    public int getRowCount() {
        return cashDesks.size();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return Boolean.FALSE;
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int row, int column) {
        CashDesk cashDesk = cashDesks.get(row);
        return switch (CashDeskTableColumn.getByIndex(column)) {
            case COL_NO -> cashDesk.getNumber();
            case COL_NUMBER_OF_CLIENTS -> ""; //cashDesk.getClients().size();
            case COL_INFO -> cashDesk.getState();
        };
    }

    @Override
    public void fireTableDataChanged() {
        super.fireTableDataChanged();
    }
}
