import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditWeaponDialog extends JDialog {
    private JTextField nameField;
    private JTextField typeField;
    private JTextField numberField;
    private JTextField priceField;
    private JTextField dateInField;
    private JTextField dateOutField;
    private Weapon weapon;

    public EditWeaponDialog(JFrame parent, Weapon weapon) {
        super(parent, "Chỉnh sửa Vũ Khí", true);
        this.weapon = weapon;

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
                weapon.setName(nameField.getText());
                weapon.setType(typeField.getText());
                weapon.setNumber(Integer.parseInt(numberField.getText()));
                weapon.setPrice(Double.parseDouble(priceField.getText()));
                weapon.setDateIn(dateInField.getText());
                weapon.setDateOut(dateOutField.getText());

                ((MainUI) parent).updateWeapon(((MainUI) parent).getSelectedRow(), weapon);
                dispose();
            }
        });
        add(saveButton);
    }
}
