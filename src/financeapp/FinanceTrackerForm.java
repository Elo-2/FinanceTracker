package financeapp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.io.FileWriter;

public class FinanceTrackerForm {

    private JPanel mainPanel;

    private JTextField PRAĆENJELIČNIHFINANSIJATextField;
    private JTextField unesiteIznosVašegPrihodaTextField;
    private JTextField opišiteOvajIzvorPrihodaTextField;

    private JTextField amountField;
    private JTextField descriptionField;

    private JComboBox<String> typeCombo;
    private JComboBox<String> categoryCombo;

    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton exportButton;

    private JTable transactionTable;

    private JLabel incomeLabel;
    private JLabel expenseLabel;
    private JLabel balanceLabel;



    private TransactionManager manager;

    private String selectedId = null;

    public FinanceTrackerForm() {

        manager = new TransactionManager();

        // sigurnost - prazna tabela ako GUI još nije učitan
        if (transactionTable != null) {

            transactionTable.getSelectionModel().addListSelectionListener(e -> {

                int row = transactionTable.getSelectedRow();

                if (row >= 0) {

                    selectedId = transactionTable.getValueAt(row, 0).toString();

                    typeCombo.setSelectedItem(transactionTable.getValueAt(row, 1));
                    categoryCombo.setSelectedItem(transactionTable.getValueAt(row, 2));
                    amountField.setText(transactionTable.getValueAt(row, 3).toString());
                    descriptionField.setText(transactionTable.getValueAt(row, 4).toString());
                }
            });
        }

        loadDataIntoTable();
        updateSummary();

        // ADD
        addButton.addActionListener(e -> {

            try {

                Transaction t = new Transaction(
                        null,
                        (String) typeCombo.getSelectedItem(),
                        (String) categoryCombo.getSelectedItem(),
                        Double.parseDouble(amountField.getText()),
                        descriptionField.getText()
                );

                manager.addTransaction(t);

                loadDataIntoTable();
                updateSummary();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Greška pri unosu!");
            }
        });

        // UPDATE
        updateButton.addActionListener(e -> {

            if (selectedId == null) {
                JOptionPane.showMessageDialog(null, "Odaberi red!");
                return;
            }

            Transaction t = new Transaction(
                    selectedId,
                    (String) typeCombo.getSelectedItem(),
                    (String) categoryCombo.getSelectedItem(),
                    Double.parseDouble(amountField.getText()),
                    descriptionField.getText()
            );

            manager.updateTransaction(selectedId, t);

            loadDataIntoTable();
            updateSummary();

            selectedId = null;
        });

        // DELETE
        deleteButton.addActionListener(e -> {

            if (selectedId == null) {
                JOptionPane.showMessageDialog(null, "Odaberi red!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Jeste li sigurni?",
                    "Brisanje",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {

                manager.deleteTransaction(selectedId);

                loadDataIntoTable();
                updateSummary();

                selectedId = null;
            }
        });

        // EXPORT
        exportButton.addActionListener(e -> {

            try {

                FileWriter writer = new FileWriter("export.txt");

                double income = manager.getTotalIncome();
                double expense = manager.getTotalExpense();
                double balance = income - expense;

                writer.write("Ukupni prihod: " + income + "\n");
                writer.write("Ukupni rashod: " + expense + "\n");
                writer.write("Stanje: " + balance + "\n\n");

                writer.write("Rashodi po kategorijama:\n");

                double hrana = 0;
                double prevoz = 0;
                double zabava = 0;
                double racuni = 0;

                for (Transaction t : manager.getAllTransactions()) {

                    if (t.getType().equals("Rashod")) {

                        switch (t.getCategory()) {

                            case "Hrana":
                                hrana += t.getAmount();
                                break;

                            case "Prevoz":
                                prevoz += t.getAmount();
                                break;

                            case "Zabava":
                                zabava += t.getAmount();
                                break;

                            case "Racuni":
                                racuni += t.getAmount();
                                break;
                        }
                    }
                }

                writer.write("Hrana: " + hrana + "\n");
                writer.write("Prevoz: " + prevoz + "\n");
                writer.write("Zabava: " + zabava + "\n");
                writer.write("Racuni: " + racuni + "\n");

                writer.close();

                JOptionPane.showMessageDialog(null, "Export završen!");

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Greška pri exportu!");
            }
        });
    }

    private void loadDataIntoTable() {

        ArrayList<Transaction> list = manager.getAllTransactions();

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Vrsta");
        model.addColumn("Kategorija");
        model.addColumn("Iznos");
        model.addColumn("Opis");

        for (Transaction t : list) {

            model.addRow(new Object[]{
                    t.getId(),
                    t.getType(),
                    t.getCategory(),
                    t.getAmount(),
                    t.getDescription()
            });
        }

        transactionTable.setModel(model);

        // ponovo aktiviraj klik listener nakon refresh-a
        transactionTable.getSelectionModel().addListSelectionListener(e -> {

            int row = transactionTable.getSelectedRow();

            if (row >= 0) {

                selectedId = transactionTable.getValueAt(row, 0).toString();

                typeCombo.setSelectedItem(transactionTable.getValueAt(row, 1));
                categoryCombo.setSelectedItem(transactionTable.getValueAt(row, 2));
                amountField.setText(transactionTable.getValueAt(row, 3).toString());
                descriptionField.setText(transactionTable.getValueAt(row, 4).toString());
            }
        });
    }

    private void updateSummary() {

        double income = manager.getTotalIncome();
        double expense = manager.getTotalExpense();

        incomeLabel.setText("Prihod: " + income);
        expenseLabel.setText("Rashod: " + expense);
        balanceLabel.setText("Saldo: " + (income - expense));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}