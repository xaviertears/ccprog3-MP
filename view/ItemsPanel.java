// ItemsPanel.java

package view;
import controller.ItemsController;
import model.Item;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ItemsPanel extends JPanel {
    private ItemsController controller;
    private DefaultTableModel tableModel;

    public ItemsPanel(ItemsController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(5,5));

        JPanel form = new JPanel(new GridLayout(2,3,5,5));
        JTextField nameField = new JTextField();
        JTextField catField  = new JTextField();
        JTextField effField  = new JTextField();
        form.add(new JLabel("Name:")); form.add(nameField);
        form.add(new JLabel("Category:")); form.add(catField);
        form.add(new JLabel("Effect:")); form.add(effField);
        add(form, BorderLayout.NORTH);

        String[] cols = {"Name","Category","Effect"};
        tableModel = new DefaultTableModel(cols, 0);
        JTable table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel btns = new JPanel();
        JButton addBtn = new JButton("Add");
        JButton searchBtn = new JButton("Search");
        btns.add(addBtn); btns.add(searchBtn);
        add(btns, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> {
            controller.addItem(nameField.getText(), catField.getText(), effField.getText());
            refreshTable(controller.getAllItems());
        });
        searchBtn.addActionListener(e -> {
            List<Item> filtered = controller.searchItems(nameField.getText());
            refreshTable(filtered);
        });
    }

    private void refreshTable(List<Item> list) {
        tableModel.setRowCount(0);
        for (Item i : list) {
            tableModel.addRow(new Object[]{i.getName(), i.getCategory(), i.getEffect()});
        }
    }
}
