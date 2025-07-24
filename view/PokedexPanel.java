import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PokedexPanel extends JPanel {
    private PokedexController controller;
    private DefaultTableModel tableModel;

    public PokedexPanel(PokedexController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(5,5));

        // Input form
        JPanel form = new JPanel(new GridLayout(2,4,5,5));
        JTextField noField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField t1Field = new JTextField();
        JTextField t2Field = new JTextField();
        form.add(new JLabel("No:")); form.add(noField);
        form.add(new JLabel("Name:")); form.add(nameField);
        form.add(new JLabel("Type1:")); form.add(t1Field);
        form.add(new JLabel("Type2:")); form.add(t2Field);
        add(form, BorderLayout.NORTH);

        // Data table
        String[] cols = {"No","Name","Type1","Type2"};
        tableModel = new DefaultTableModel(cols, 0);
        JTable table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Buttons
        JPanel btns = new JPanel();
        JButton addBtn = new JButton("Add");
        JButton searchBtn = new JButton("Search");
        btns.add(addBtn); btns.add(searchBtn);
        add(btns, BorderLayout.SOUTH);

        // Action listeners
        addBtn.addActionListener(e -> {
            int no = Integer.parseInt(noField.getText());
            controller.addPokemon(no, nameField.getText(), t1Field.getText(), t2Field.getText());
            refreshTable(controller.getAllPokemon());
        });
        searchBtn.addActionListener(e -> {
            List<Pokemon> filtered = controller.searchPokemon(nameField.getText());
            refreshTable(filtered);
        });
    }

    private void refreshTable(List<Pokemon> list) {
        tableModel.setRowCount(0);
        for (Pokemon p : list) {
            tableModel.addRow(new Object[]{p.getPokedexNumber(), p.getName(), p.getType1(), p.getType2()});
        }
    }
}
