import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExportWeaponDialog extends JDialog {
    private JTextField exportQuantityField;
    private JTextField exportDateField;

    public ExportWeaponDialog(JFrame parent, Weapon weapon, int index) {
        super(parent, "Xuất Vũ Khí", true);
        setSize(300, 200);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(3, 2));

        exportQuantityField = new JTextField();
        exportDateField = new JTextField();

        add(new JLabel("Số Lượng Xuất:"));
        add(exportQuantityField);
        add(new JLabel("Ngày Xuất:"));
        add(exportDateField);

        JButton saveButton = new JButton("Xuất");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int quantity = Integer.parseInt(exportQuantityField.getText());
                    String exportDate = exportDateField.getText();

                    if (quantity > 0 && quantity <= weapon.getNumber()) {
                        ((MainUI) parent).addExportRecord(weapon.getName(), quantity, exportDate);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(ExportWeaponDialog.this, "Số lượng xuất không hợp lệ.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ExportWeaponDialog.this, "Định dạng số không hợp lệ.");
                }
            }
        });

        add(saveButton);
    }
}
