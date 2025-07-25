
package view;
import controller.MovesController;
import model.Move;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MovesPanel extends JPanel {
    private MovesController controller;
    private DefaultTableModel tableModel;

    public MovesPanel(MovesController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(5,5));

        JPanel form = new JPanel(new GridLayout(2,3,5,5));
        JTextField nameField = new JTextField();
        JTextField classField = new JTextField();
        JTextField typeField = new JTextField();
        form.add(new JLabel("Name:")); form.add(nameField);
        form.add(new JLabel("Class:")); form.add(classField);
        form.add(new JLabel("Type:")); form.add(typeField);
        add(form, BorderLayout.NORTH);

        String[] cols = {"Name","Class","Type"};
        tableModel = new DefaultTableModel(cols, 0);
        JTable table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel btns = new JPanel();
        JButton addBtn = new JButton("Add");
        JButton searchBtn = new JButton("Search");
        btns.add(addBtn); btns.add(searchBtn);
        add(btns, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> {
            controller.addMove(nameField.getText(), classField.getText(), typeField.getText());
            refreshTable(controller.getAllMoves());
        });
        searchBtn.addActionListener(e -> {
            List<Move> filtered = controller.searchMoves(nameField.getText());
            refreshTable(filtered);
        });
    }

    private void refreshTable(List<Move> list) {
        tableModel.setRowCount(0);
        for (Move m : list) {
            tableModel.addRow(new Object[]{m.getName(), m.getClassification(), m.getType1()});
        }
    }
}
