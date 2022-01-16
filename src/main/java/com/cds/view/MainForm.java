package com.cds.view;

import com.cds.view.tablemodel.ShopTableModel;
import lombok.*;
import net.coderazzi.filters.gui.TableFilterHeader;

import javax.swing.*;

@Getter
public class MainForm {
    private JFrame frame;
    private JPanel mainPanel;
    private JTextField textField1;
    private JButton btnAccept;
    private JTable tableShop;
    private JLabel lblNumberOfClients;

    public MainForm() {
        frame = new JFrame("Cash Desk Simulator");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }

    private void createUIComponents() {
        this.createTableShop();
    }

    private void createTableShop() {
        tableShop = new JTable();
        TableFilterHeader customTableFilterHeader = new TableFilterHeader(tableShop);
        tableShop.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableShop.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableShop.setModel(new ShopTableModel());
    }
}
