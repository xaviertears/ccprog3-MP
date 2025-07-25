package view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TrainersPanel extends JPanel {
    private TrainersController controller;
    private DefaultTableModel tableModel;

    public TrainersPanel(TrainersController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(5,5));

        JPanel form = new JPanel(new GridLayout(2,3,5,5));
        JTextField idField    = new JTextField();
        JTextField nameField  = new JTextField();
        JTextField moneyField = new JTextField();
        form.add(new JLabel("ID:"));    form.add(idField);
        form.add(new JLabel("Name:"));  form.add(nameField);
        form.add(new JLabel("Money:")); form.add(moneyField);
        add(form, BorderLayout.NORTH);

        String[] cols = {"ID","Name","Money"};
        tableModel = new DefaultTableModel(cols, 0);
        JTable table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel btns = new JPanel();
        JButton addBtn    = new JButton("Add");
        JButton searchBtn = new JButton("Search");
        btns.add(addBtn); btns.add(searchBtn);
        add(btns, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> {
            int id = Integer.parseInt(idField.getText());
            double m = Double.parseDouble(moneyField.getText());
            controller.addTrainer(id, nameField.getText(), m);
            refreshTable(controller.getAllTrainers());
        });

        searchBtn.addActionListener(e -> {
            List<Trainer> filtered = controller.searchTrainers(nameField.getText());
            refreshTable(filtered);
        });
    }

    private void refreshTable(List<Trainer> list) {
        tableModel.setRowCount(0);
        for (Trainer t : list) {
            tableModel.addRow(new Object[]{t.getId(), t.getName(), t.getMoney()});
        }
    }
}
