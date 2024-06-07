import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditWeaponDetailsDialog extends JDialog {
    private JTextField nameField;
    private JTextField typeField;
    private JTextField numberField;
    private JTextField priceField;
    private JTextField dateInField;
    private JTextField dateOutField;

    public EditWeaponDetailsDialog(JFrame parent, Weapon weapon, int index) {
        super(parent, "Chỉnh Sửa Thông Tin Vũ Khí", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(7, 2));

        nameField = new JTextField(weapon.getName());
        typeField = new JTextField(weapon.getType());
        numberField = new JTextField(String.valueOf(weapon.getNumber()));
        priceField = new JTextField(String.valueOf(weapon.getPrice()));
        dateInField = new JTextField(weapon.getDateIn());
        dateOutField = new JTextField(weapon.getDateOut());

        add(new JLabel("Tên:"));
        add(nameField);
        add(new JLabel("Loại:"));
        add(typeField);
        add(new JLabel("Số Lượng:"));
        add(numberField);
        add(new JLabel("Giá:"));
        add(priceField);
        add(new JLabel("Ngày Nhập:"));
        add(dateInField);
        add(new JLabel("Ngày Xuất:"));
        add(dateOutField);

        JButton saveButton = new JButton("Lưu");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    String type = typeField.getText();
                    int number = Integer.parseInt(numberField.getText());
                    double price = Double.parseDouble(priceField.getText());
                    String dateIn = dateInField.getText();
                    String dateOut = dateOutField.getText();

                    weapon.setName(name);
                    weapon.setType(type);
                    weapon.setNumber(number);
                    weapon.setPrice(price);
                    weapon.setDateIn(dateIn);
                    weapon.setDateOut(dateOut);

                    ((MainUI) parent).updateWeapon(index, weapon);
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(EditWeaponDetailsDialog.this, "Định dạng số không hợp lệ.");
                }
            }
        });
        add(saveButton);
    }
}
