import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddWeaponDialog extends JDialog {
    private JTextField nameField;
    private JTextField typeField;
    private JTextField numberField;
    private JTextField priceField;
    private JTextField dateInField;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public AddWeaponDialog(JFrame parent) {
        super(parent, "Thêm Vũ Khí", true);
        setSize(400, 250);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(6, 2));

        nameField = new JTextField();
        typeField = new JTextField();
        numberField = new JTextField();
        priceField = new JTextField();
        dateInField = new JTextField();

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

        JButton addButton = new JButton("Thêm");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String type = typeField.getText();
                int number;
                double price;
                String dateIn = dateInField.getText();

                try {
                    number = Integer.parseInt(numberField.getText());
                    price = Double.parseDouble(priceField.getText());
                    if (isValidDate(dateIn)) {
                        Weapon newWeapon = new Weapon(name, type, number, price, dateIn, "");
                        ((MainUI) parent).addWeapon(newWeapon);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(AddWeaponDialog.this, "Ngày không chính xác. Vui lòng nhập lại.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AddWeaponDialog.this, "Vui lòng nhập số hợp lệ cho Số Lượng và Giá.");
                }
            }
        });
        add(addButton);
    }

    private boolean isValidDate(String date) {
        try {
            dateFormat.setLenient(false);
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public int getNumber() {
        return Integer.parseInt(numberField.getText());
    }

    public double getPrice() {
        return Double.parseDouble(priceField.getText());
    }
}
