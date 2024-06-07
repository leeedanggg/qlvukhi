import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class WeaponManagementDialog extends JDialog {
    private JTextField nameField;
    private JTextField quantityField;
    private JTextField dateField;
    private JTextArea reasonArea;

    public WeaponManagementDialog(JFrame parent, ArrayList<Weapon> weapons) {
        super(parent, "Quản lý Vũ Khí", true);

        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(7, 2));

        JLabel nameLabel = new JLabel("Tên vũ khí:");
        nameField = new JTextField();
        add(nameLabel);
        add(nameField);

        JLabel quantityLabel = new JLabel("Số lượng:");
        quantityField = new JTextField();
        add(quantityLabel);
        add(quantityField);

        JLabel dateLabel = new JLabel("Ngày:");
        dateField = new JTextField();
        add(dateLabel);
        add(dateField);

        JLabel reasonLabel = new JLabel("Lý do:");
        reasonArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(reasonArea);
        add(reasonLabel);
        add(scrollPane);

        JButton exportButton = new JButton("Xuất");
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAction("Xuất", weapons);
            }
        });
        add(exportButton);

        JButton importButton = new JButton("Nhập");
        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAction("Nhập", weapons);
            }
        });
        add(importButton);

        JButton saveButton = new JButton("Lưu");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAction("Xuất", weapons); // Mặc định chọn "Xuất" nếu người dùng nhấn "Lưu" trước
            }
        });
        add(saveButton);
    }

    private void handleAction(String action, ArrayList<Weapon> weapons) {
        String name = nameField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        String date = dateField.getText();
        String reason = reasonArea.getText();

        if (isValidDate(date)) {
            Weapon weapon = new Weapon(name, "", quantity, 0, date, "");
            weapon.setReason(reason);
            // Thêm thuộc tính action vào weapon
            weapon.setAction(action);
            weapons.add(weapon);
            JOptionPane.showMessageDialog(WeaponManagementDialog.this, "Thêm vũ khí thành công!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(WeaponManagementDialog.this, "Ngày không hợp lệ. Vui lòng nhập theo định dạng dd/MM/yyyy.");
        }
    }

    private boolean isValidDate(String date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);
            dateFormat.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
